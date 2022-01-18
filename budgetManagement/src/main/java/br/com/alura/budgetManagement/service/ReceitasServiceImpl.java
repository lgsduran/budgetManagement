package br.com.alura.budgetManagement.service;

import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.repository.ReceitasRepository;
import br.com.alura.budgetManagement.request.ReceitaRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
@Service
public class ReceitasServiceImpl implements IReceitasService {
	
	private ReceitasRepository receitasRepository;

	@Override
	public Receitas addReceita(ReceitaRequest request) throws BusinessException {
		log.info("Receita added.");
		return receitasRepository.save(request.toEntity());
	}

}
