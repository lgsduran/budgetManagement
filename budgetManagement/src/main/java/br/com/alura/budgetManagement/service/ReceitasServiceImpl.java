package br.com.alura.budgetManagement.service;

import static java.lang.String.format;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.repository.ReceitasRepository;
import br.com.alura.budgetManagement.request.AddReceitaRequest;
import br.com.alura.budgetManagement.request.AlterReceitaRequest;
import br.com.alura.budgetManagement.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ReceitasServiceImpl implements IReceitasService {
	
	private ReceitasRepository receitasRepository;	

	public ReceitasServiceImpl(ReceitasRepository receitasRepository) {
		this.receitasRepository = receitasRepository;
	}

	@Override
	public Receitas addReceita(AddReceitaRequest request) throws BusinessException {
		Predicate<Receitas> month = x -> x.getData().getMonthValue() == request.getData().getMonthValue();
        Predicate<Receitas> year = x -> x.getData().getYear() ==  request.getData().getYear();
		Optional<Receitas> descricao = this.receitasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(month.and(year))
				.findFirst();
		
		if (descricao.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));
		
		log.info("Receita added successfully.");
		return receitasRepository.save(request.toEntity());
	}

	@Override
	public Page<Receitas> listReceitas(Pageable pageable) {
		return this.receitasRepository.findAll(pageable);
	}

	@Override
	public Receitas getReceitaById(long id) throws BusinessException {
		return this.receitasRepository.findById(id)
			      .orElseThrow(() -> new BusinessException(format("Id %s not found.", id)));
	}

	@Override
	public Receitas alterReceita(Long id, AlterReceitaRequest request) throws BusinessException {
		Optional<Receitas> mes = this.receitasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(d -> d.getData().getMonthValue() == request.getData().getMonthValue())
				.findFirst();
		
		if (mes.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));
		
		 Optional<Receitas> receitaDB = Stream.of(id)
				      .map(this.receitasRepository::findById)
				      .filter(r -> r != null)
				      .findFirst()
				      .orElseThrow(() -> new BusinessException(format("Id %s not found.", id)));
		 
		 Receitas receita = request.changeReceita(receitaDB.get());
		 log.info("Receita updated successfully.");
		 return this.receitasRepository.save(receita);
	}

	@Override
	public Response deleteReceita(long id) throws BusinessException {
		Receitas receita = this.receitasRepository.findById(id)
					      .orElseThrow(() -> new BusinessException(format("Id %s not found.", id)));
		
		this.receitasRepository.delete(receita);
		return new Response("Register deleted successfully.");
	}

}
