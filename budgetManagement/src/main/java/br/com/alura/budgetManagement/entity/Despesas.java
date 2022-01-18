package br.com.alura.budgetManagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.alura.budgetManagement.enums.DescricaoDespesas;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(	name = "despesas")
public class Despesas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "despesa_id")
	private long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	private DescricaoDespesas descricao;
	
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	private Double valor;
	
	@NotNull(message = "Field must not be null.")
	@NotEmpty(message = "Field must not be empty.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate data;	

}
