package br.com.alura.budgetManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.enums.DescricaoReceitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

	List<Receitas> findAllByDescricao(DescricaoReceitas descricao);

	
	Boolean existsByDescricao(DescricaoReceitas receita);

}
