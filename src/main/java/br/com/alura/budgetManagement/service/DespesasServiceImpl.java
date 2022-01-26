package br.com.alura.budgetManagement.service;

import static br.com.alura.budgetManagement.enums.CategoriaType.OUTRAS;
import static java.lang.String.format;
import static java.util.EnumSet.allOf;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.DescricaoDespesasType;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.helpers.SupplierHelp;
import br.com.alura.budgetManagement.repository.DespesasRepository;
import br.com.alura.budgetManagement.repository.ReceitasRepository;
import br.com.alura.budgetManagement.request.AddDespesaRequest;
import br.com.alura.budgetManagement.request.AlterDespesaRequest;
import br.com.alura.budgetManagement.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DespesasServiceImpl implements IDespesasService {
	
	private SupplierHelp sh = new SupplierHelp();
	
	private DespesasRepository despesasRepository;
	private ReceitasRepository receitasRepository;	

	public DespesasServiceImpl(DespesasRepository despesasRepository, ReceitasRepository receitasRepository) {
		this.despesasRepository = despesasRepository;
		this.receitasRepository = receitasRepository;
	}

	@Override
	public Despesas addDespesas(AddDespesaRequest request) throws BusinessException {
		
		Optional<Despesas> descricao = this.despesasRepository.
				findAllByDescricao(request.getDescricao())
				.stream()
				.filter(sh.isMonthSavedDespesa(request.getData().getMonthValue())
						.and(sh.isYearSavedDespesa(request.getData().getYear())))
				.findFirst();
		
		if (descricao.isPresent())
			throw new BusinessException(format("Month %s already taken.", request.getData().getMonth()));		
		
		if (request.getCategoria() == null)
			request.setCategoria(OUTRAS);
		
		List<Receitas> receitas = receitasRepository.findAll()
			.stream()
			.filter(sh.isMonthSavedReceita(request.getData().getMonthValue())
			.and(sh.isYearSavedReceita(request.getData().getYear())))
			.collect(Collectors.toList());
		
		double totalReceitas = 0;
		for (Receitas receita : receitas) 
			totalReceitas = totalReceitas + receita.getValor();
		
		List<Despesas> despesas = despesasRepository.findAll()
				.stream()
				.filter(sh.isMonthSavedDespesa(request.getData().getMonthValue())
				.and(sh.isYearSavedDespesa(request.getData().getYear())))
				.collect(Collectors.toList());
			
			double totalDespesas = 0;
			for (Despesas despesa : despesas) 
				totalDespesas = totalDespesas + despesa.getValor();
		
		if (totalReceitas <= request.getValor() || totalDespesas >= totalReceitas)
			throw new BusinessException(format("Despesas' total amount must be less than %s.", totalReceitas));	
		
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
				.filter(sh.isMonthSavedDespesa(request.getData().getMonthValue())
						.and(sh.isYearSavedDespesa(request.getData().getYear())))
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
	
	@Override
	public List<Despesas> listDespesasByDescricao(String descricao) throws BusinessException {
		DescricaoDespesasType typeResult = null;
		for (DescricaoDespesasType type : allOf(DescricaoDespesasType.class)) {
			if (type.getValue().equalsIgnoreCase(descricao))
				typeResult = type;
		}
		
		if (typeResult == null)
			throw new BusinessException(format("Descricao %s was not found.", descricao));
		
		return this.despesasRepository.findAllByDescricao(typeResult);		
	}

	@Override
	public List<Despesas> listDespesasByYearMonth(int year, int month) throws BusinessException {
		List<Despesas> results = this.despesasRepository.findAll();
		
		List<Despesas> resultYear = results.stream()
			   .filter(x -> x.getData().getYear() == year)
			   .collect(Collectors.toList());
		
		if (resultYear.isEmpty())
			throw new BusinessException(format("Year %s was not found.", year));
		
		List<Despesas> resultMonth = resultYear.stream()
				   .filter(x -> x.getData().getMonthValue() == month)
				   .collect(Collectors.toList());
		
		if (resultMonth.isEmpty())
			throw new BusinessException(format("Month %s was not found.", month));
		
		return resultMonth;		
	}	
}
