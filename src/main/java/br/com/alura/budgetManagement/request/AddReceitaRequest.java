package br.com.alura.budgetManagement.request;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.DescricaoReceitasType;
import lombok.Data;

@Data
public class AddReceitaRequest {
	
	@Enumerated(EnumType.STRING)
	private DescricaoReceitasType descricao;
	
	@NotNull(message = "Field must not be null.")
	private Double valor;
	
	@NotNull(message = "Field must not be null.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;	
	
	public Receitas toEntity() {
		Receitas receitas = new Receitas();
		BeanUtils.copyProperties(this, receitas);
		return receitas;
	}
	

}
