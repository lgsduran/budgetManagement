package br.com.alura.budgetManagement.service;

import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.LoginRequest;
import br.com.alura.budgetManagement.request.SignupRequest;
import br.com.alura.budgetManagement.response.JwtResponse;
import br.com.alura.budgetManagement.response.Response;

public interface IAuthService {
	
	JwtResponse authenticateUser(LoginRequest loginRequest);
	
	Response registerUser(SignupRequest signUpRequest) throws BusinessException;
		

}
