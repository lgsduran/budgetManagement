package br.com.alura.budgetManagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DescricaoDespesasType {
	
	ALUGUEL("ALUGUEL"),
	PRESTACAO_CARRO("PRESTACAO_CARRO"),
	AGUA("AGUA"),
	LUZ("LUZ"),
	INTERNET("INTERNET"),
	CELULAR("CELULAR");	
	
	private final String value;


}
