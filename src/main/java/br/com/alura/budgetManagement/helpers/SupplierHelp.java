package br.com.alura.budgetManagement.helpers;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.entity.Receitas;

public class SupplierHelp {
	
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
	
	public <T> Double getAmount(List<T> list, 
			Predicate<? super T> predicate, 
			ToDoubleFunction<? super T> mapper) {
		return list.stream().filter(predicate).mapToDouble(mapper).sum();
	}
	
	public <T> List<T> getRegisters(List<T> list, Predicate<? super T> p1, Predicate<? super T> p2) {
		return list.stream().filter(p1).filter(p2).collect(Collectors.toList());
	}

}
