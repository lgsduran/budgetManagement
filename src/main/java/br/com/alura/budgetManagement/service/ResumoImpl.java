package br.com.alura.budgetManagement.service;

import static br.com.alura.budgetManagement.response.ResumoResponse.builder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.CategoriaType;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.helpers.SupplierHelp;
import br.com.alura.budgetManagement.repository.DespesasRepository;
import br.com.alura.budgetManagement.repository.ReceitasRepository;
import br.com.alura.budgetManagement.response.ResumoResponse;

@Service
public class ResumoImpl implements IResumo {
	
	private SupplierHelp sh = new SupplierHelp();
	
	private DespesasRepository despesasRepository;
	private ReceitasRepository receitasRepository;	
	
	public ResumoImpl(DespesasRepository despesasRepository, ReceitasRepository receitasRepository) {
		this.despesasRepository = despesasRepository;
		this.receitasRepository = receitasRepository;
	}

	@Override
	public ResumoResponse generateResumo(int year, int month) throws BusinessException{
		List<Receitas> receitas = sh.getRegisters(receitasRepository.findAll(),
				x -> x.getData().getYear() == year,
				x -> x.getData().getMonthValue() == month);
		
		if (receitas.isEmpty()) 
			throw new BusinessException("Nothing found! Please, check the inputs.");		
		
		List<Despesas> despesas = sh.getRegisters(despesasRepository.findAll(),
				x -> x.getData().getYear() == year,
				x -> x.getData().getMonthValue() == month);
		
		if (despesas.isEmpty())
			throw new BusinessException("Nothing found! Please, check the inputs.");
		
		double receitaTotal = receitas.stream().mapToDouble(x -> x.getValor()).sum();
		double despesaTotal = despesas.stream().mapToDouble(x -> x.getValor()).sum();
		Map<CategoriaType, Double> categorias = despesas.stream().collect(
				groupingBy(Despesas::getCategoria, 
						summingDouble(Despesas::getValor)));
		
		return builder()
			.Valor_total_das_receitas_no_mes(receitaTotal)
			.Valor_total_das_despesas_no_mes(despesaTotal)
			.Saldo_final_no_mes(receitaTotal - despesaTotal)
			.Categoria(categorias)
			.build();
	}
}
