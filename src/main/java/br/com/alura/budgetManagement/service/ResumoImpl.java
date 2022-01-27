package br.com.alura.budgetManagement.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		ResumoResponse resumo = new ResumoResponse();
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
				Collectors.groupingBy(Despesas::getCategoria, 
						Collectors.summingDouble(Despesas::getValor)));
		
		resumo.setValor_total_das_receitas_no_mes(receitaTotal);
		resumo.setValor_total_das_despesas_no_mes(despesaTotal);
		resumo.setSaldo_final_no_mes(receitaTotal - despesaTotal);		
		resumo.setCategoria(categorias);
		
		return resumo;
	}

}
