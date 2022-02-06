package br.com.alura.budgetManagement.service;

import org.springframework.http.ResponseEntity;

import br.com.alura.budgetManagement.request.LoginRequest;
import br.com.alura.budgetManagement.request.SignupRequest;

public interface IAuthService {
	
	ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

	ResponseEntity<?> registerUser(SignupRequest signUpRequest);
		

}
