package br.com.alura.budgetManagement.helpers;

import java.util.function.Predicate;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.entity.Receitas;

public class SupplierHelp {
	
	public boolean isValueSaved(int value1, int value2) {
		return value1 == value2;
	}
	
	public Predicate<Receitas> isMonthSavedReceita(int monthValue) {
	    return x -> x.getData().getMonthValue() == monthValue;
	}
	
	public Predicate<Receitas> isYearSavedReceita(int yearValue) {
	    return x -> x.getData().getYear() == yearValue;
	}
	
	public Predicate<Despesas> isMonthSavedDespesa(int monthValue) {
	    return x -> x.getData().getMonthValue() == monthValue;
	}
	
	public Predicate<Despesas> isYearSavedDespesa(int yearValue) {
	    return x -> x.getData().getYear() == yearValue;
	}

}
