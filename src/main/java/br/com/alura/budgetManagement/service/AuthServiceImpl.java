package br.com.alura.budgetManagement.service;

import static br.com.alura.budgetManagement.enums.RoleType.ADMIN;
import static br.com.alura.budgetManagement.enums.RoleType.USER;
import static org.springframework.util.StringUtils.hasText;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Role;
import br.com.alura.budgetManagement.entity.User;
import br.com.alura.budgetManagement.repository.RoleRepository;
import br.com.alura.budgetManagement.repository.UserRepository;
import br.com.alura.budgetManagement.request.LoginRequest;
import br.com.alura.budgetManagement.request.SignupRequest;
import br.com.alura.budgetManagement.response.JwtResponse;
import br.com.alura.budgetManagement.response.Response;
import br.com.alura.budgetManagement.security.UserDetailsImpl;
import br.com.alura.budgetManagement.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder encoder;

	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@Override
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new Response("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new Response("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {

			if (!hasText(role))
				role = "USER";

			switch (role) {
			case "ADMIN":
				roles.add(roleRepository.findByName(ADMIN));
				break;
			case "USER":
				roles.add(roleRepository.findByName(USER));
				break;
			default:
				throw new RuntimeException("Error: Role is not found. Make sure it is uppercase!");
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new Response("User registered successfully!"));

	}

}
