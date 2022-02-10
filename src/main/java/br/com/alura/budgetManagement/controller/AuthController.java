package br.com.alura.budgetManagement.controller;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.LoginRequest;
import br.com.alura.budgetManagement.request.SignupRequest;
import br.com.alura.budgetManagement.response.JwtResponse;
import br.com.alura.budgetManagement.response.Response;
import br.com.alura.budgetManagement.service.AuthServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping()
public class AuthController {

	private AuthServiceImpl authService;

	@PostMapping("/signin")
	@ResponseStatus(CREATED)
	public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return this.authService.authenticateUser(loginRequest);

	}

	@PostMapping("/signup")
	@ResponseStatus(CREATED)
	public Response registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws BusinessException {
		return this.authService.registerUser(signUpRequest);

	}

}
