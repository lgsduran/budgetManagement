package br.com.alura.budgetManagement.service;

import static java.lang.String.format;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.repository.DespesasRepository;
import br.com.alura.budgetManagement.request.AddDespesaRequest;
import br.com.alura.budgetManagement.request.AlterDespesaRequest;
import br.com.alura.budgetManagement.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DespesasServiceImpl implements IDespesasService {
	
	private DespesasRepository despesasRepository;	

	public DespesasServiceImpl(DespesasRepository despesasRepository) {
		this.despesasRepository = despesasRepository;
	}

	@Override
	public Despesas addDespesas(AddDespesaRequest request) throws BusinessException {
		Optional<Despesas> descricao = this.despesasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(isMonthSaved(request.getData().getMonthValue())
						.and(isYearSaved(request.getData().getYear())))
				.findFirst();
		
		if (descricao.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));
		
		log.info("Receita added successfully.");
		return despesasRepository.save(request.toEntity());
	}

	@Override
	public Page<Despesas> listDespesas(Pageable pageable) {
		return this.despesasRepository.findAll(pageable);
	}

	@Override
	public Despesas getDespesasById(long id) throws BusinessException {
		return this.despesasRepository.findById(id)
			      .orElseThrow(() -> new BusinessException(format("Id %s was not found.", id)));
	}

	@Override
	public Despesas alterDespesa(Long id, AlterDespesaRequest request) throws BusinessException {
		Optional<Despesas> descricao = this.despesasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(isMonthSaved(request.getData().getMonthValue())
						.and(isYearSaved(request.getData().getYear())))
				.findFirst();
		
		if (descricao.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));
		
		 Optional<Despesas> despesaDB = Stream.of(id)
				      .map(this.despesasRepository::findById)
				      .findFirst().get();
		 
		 if (despesaDB.isEmpty()) 
			 throw new BusinessException(format("Id %s was not found.", id));				      
		 
		 Despesas despesas = request.changeDespesas(despesaDB.get());
		 log.info("Receita updated successfully.");
		 return this.despesasRepository.save(despesas);
	}

	@Override
	public Response deleteDespesa(long id) throws BusinessException {
		Despesas despesas = this.despesasRepository.findById(id)
					      .orElseThrow(() -> new BusinessException(format("Id %s was not found.", id)));
		
		this.despesasRepository.delete(despesas);
		return new Response("Register deleted successfully.");
	}
	
	private Predicate<Despesas> isMonthSaved(int monthValue) {
	    return x -> x.getData().getMonthValue() == monthValue;
	}
	
	private Predicate<Despesas> isYearSaved(int yearValue) {
	    return x -> x.getData().getYear() == yearValue;
	}

}
