package br.com.alura.budgetManagement.service;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.ReceitaRequest;

public interface IReceitasService {
	
	Receitas addReceita(ReceitaRequest request) throws BusinessException;

}
