package br.com.alura.budgetManagement.service;

import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.response.ResumoResponse;

public interface IResumo {
	
	ResumoResponse generateResumo(int year, int month) throws BusinessException;

}
