package br.com.alura.budgetManagement.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.alura.budgetManagement.enums.DescricaoReceitasType;
import lombok.Data;

@Data
public class DescricaoReceitasRequest {
	
	@Enumerated(EnumType.STRING)
	private DescricaoReceitasType descricao;

}
