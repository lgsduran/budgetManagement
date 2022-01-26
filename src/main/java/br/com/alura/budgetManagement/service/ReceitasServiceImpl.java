package br.com.alura.budgetManagement.service;

import static java.lang.String.format;
import static java.util.EnumSet.allOf;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.DescricaoReceitasType;
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
		Optional<Receitas> descricao = this.receitasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(isMonthSaved(request.getData().getMonthValue())
						.and(isYearSaved(request.getData().getYear())))
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
			      .orElseThrow(() -> new BusinessException(format("Id %s was not found.", id)));
	}

	@Override
	public Receitas alterReceita(Long id, AlterReceitaRequest request) throws BusinessException {
		Optional<Receitas> descricao = this.receitasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(isMonthSaved(request.getData().getMonthValue())
						.and(isYearSaved(request.getData().getYear())))
				.findFirst();
		
		if (descricao.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));
		
		 Optional<Receitas> receitaDB = Stream.of(id)
				      .map(this.receitasRepository::findById)
				      .findFirst().get();
		 
		 if (receitaDB.isEmpty()) 
			 throw new BusinessException(format("Id %s was not found.", id));				      
		 
		 Receitas receita = request.changeReceitas(receitaDB.get());
		 log.info("Receita updated successfully.");
		 return this.receitasRepository.save(receita);
	}

	@Override
	public Response deleteReceita(long id) throws BusinessException {
		Receitas receita = this.receitasRepository.findById(id)
					      .orElseThrow(() -> new BusinessException(format("Id %s was not found.", id)));
		
		this.receitasRepository.delete(receita);
		return new Response("Register deleted successfully.");
	}

	@Override
	public List<Receitas> listReceitasByDescricao(String descricao) throws BusinessException {
		DescricaoReceitasType typeResult = null;
		for (DescricaoReceitasType type : allOf(DescricaoReceitasType.class)) {
			if (type.getValue().equalsIgnoreCase(descricao))
				typeResult = type;
		}
		
		if (typeResult == null)
			throw new BusinessException(format("Descricao %s was not found.", descricao));
		
		return this.receitasRepository.findAllByDescricao(typeResult);		
	}
	
	@Override
	public List<Receitas> listReceitasByYearMonth(int year, int month) throws BusinessException {
		List<Receitas> results = this.receitasRepository.findAll();
		
		List<Receitas> resultYear = results.stream()
			   .filter(x -> x.getData().getYear() == year)
			   .collect(Collectors.toList());
		
		if (resultYear.isEmpty())
			throw new BusinessException(format("Year %s was not found.", year));
		
		List<Receitas> resultMonth = resultYear.stream()
				   .filter(x -> x.getData().getMonthValue() == month)
				   .collect(Collectors.toList());
		
		if (resultMonth.isEmpty())
			throw new BusinessException(format("Month %s was not found.", month));
		
		return resultMonth;		
	}
	
	private Predicate<Receitas> isMonthSaved(int monthValue) {
	    return x -> x.getData().getMonthValue() == monthValue;
	}
	
	private Predicate<Receitas> isYearSaved(int yearValue) {
	    return x -> x.getData().getYear() == yearValue;
	}


}
