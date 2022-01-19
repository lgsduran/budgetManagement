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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.budgetManagement.enums.DescricaoReceitas;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(	name = "receitas")
public class Receitas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receita_id")
	private long id;
	
	@Enumerated(EnumType.STRING)
	private DescricaoReceitas descricao;
	
	private Double valor;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;	

}
