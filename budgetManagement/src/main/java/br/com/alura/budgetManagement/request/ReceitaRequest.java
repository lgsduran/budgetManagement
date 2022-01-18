package br.com.alura.budgetManagement.request;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.DescricaoReceitas;
import lombok.Data;

@Data
public class ReceitaRequest {
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	private DescricaoReceitas descricao;
	
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	private Double valor;
	
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate data;	
	
	public Receitas toEntity() {
		Receitas receitas = new Receitas();
		BeanUtils.copyProperties(this, receitas);
		return receitas;
	}
	

}
