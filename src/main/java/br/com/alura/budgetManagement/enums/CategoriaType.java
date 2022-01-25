package br.com.alura.budgetManagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoriaType {
	
	ALIMENTACAO("ALIMENTACAO"),
	SAUDE("SAUDE"),
	MORADIA("MORADIA"),
	EDUCACAO("EDUCACAO"),
	LAZER("LAZER"),
	IMPREVISTOS("IMPREVISTOS"),
	OUTRAS("OUTRAS");
	
	private final String value;

}
