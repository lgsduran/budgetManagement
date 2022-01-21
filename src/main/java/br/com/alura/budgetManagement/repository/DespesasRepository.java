package br.com.alura.budgetManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.enums.DescricaoDespesasType;

public interface DespesasRepository extends JpaRepository<Despesas, Long>{
	
	List<Despesas> findAllByDescricao(DescricaoDespesasType descricao);

}
	