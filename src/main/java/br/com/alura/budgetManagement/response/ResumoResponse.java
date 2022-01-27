package br.com.alura.budgetManagement.response;

import java.util.HashMap;
import java.util.Map;

import br.com.alura.budgetManagement.enums.CategoriaType;
import lombok.Data;

@Data
public class ResumoResponse {
	
	private Double Valor_total_das_receitas_no_mes;
	private Double Valor_total_das_despesas_no_mes;
	private Double Saldo_final_no_mes;
	private Map<CategoriaType, Double> Categoria = new HashMap<CategoriaType, Double>();

}
