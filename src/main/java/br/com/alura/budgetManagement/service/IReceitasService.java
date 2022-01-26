package br.com.alura.budgetManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.AddReceitaRequest;
import br.com.alura.budgetManagement.request.AlterReceitaRequest;
import br.com.alura.budgetManagement.response.Response;

public interface IReceitasService {

	Receitas addReceita(AddReceitaRequest request) throws BusinessException;
	
	Page<Receitas> listReceitas(Pageable pageable);

	Receitas getReceitaById(long id) throws BusinessException;

	Receitas alterReceita(Long id, AlterReceitaRequest request) 
			throws BusinessException;

	Response deleteReceita(long id) throws BusinessException;

	List<Receitas> listReceitasByDescricao(String descricao)
			throws BusinessException;
	
	List<Receitas> listReceitasByAnosMes(int ano, int mes)
			throws BusinessException;
	
}
