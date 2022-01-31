package br.com.alura.budgetManagement.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.alura.budgetManagement.enums.CategoriaType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder(alphabetic = false)
public class ResumoResponse {
	
	private Double Valor_total_das_receitas_no_mes;
	private Double Valor_total_das_despesas_no_mes;
	private Double Saldo_final_no_mes;
	private Map<CategoriaType, Double> Categoria;

}
