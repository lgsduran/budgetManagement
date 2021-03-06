package br.com.alura.budgetManagement.request;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.enums.CategoriaType;
import br.com.alura.budgetManagement.enums.DescricaoDespesasType;
import lombok.Data;

@Data
public class AddDespesaRequest {
	
	@Enumerated(EnumType.STRING)
	private DescricaoDespesasType descricao;
	
	@NotNull(message = "Field must not be null.")
	private Double valor;
	
	@NotNull(message = "Field must not be null.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;	
	
	@Enumerated(EnumType.STRING)
	private CategoriaType categoria;
	
	public Despesas toEntity() {
		Despesas despesas = new Despesas();
		BeanUtils.copyProperties(this, despesas);
		return despesas;
	}
	

}
