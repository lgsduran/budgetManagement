package br.com.alura.budgetManagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DescricaoReceitas {
	
	SALARIO("SALARIO"),
	RENDA_EXTRA("RENDA_EXTRA"),
	OUTROS("OUTROS");	
	
	private final String value;


}
