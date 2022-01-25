package br.com.alura.budgetManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.AddDespesaRequest;
import br.com.alura.budgetManagement.request.AlterDespesaRequest;
import br.com.alura.budgetManagement.response.Response;

public interface IDespesasService {

	Despesas addDespesas(AddDespesaRequest request) throws BusinessException;

	Page<Despesas> listDespesas(Pageable pageable);

	Despesas getDespesasById(long id) throws BusinessException;

	Despesas alterDespesa(Long id, AlterDespesaRequest request) 
			throws BusinessException;

	Response deleteDespesa(long id) throws BusinessException;

}