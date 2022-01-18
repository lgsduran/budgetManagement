package br.com.alura.budgetManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.budgetManagement.entity.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

}
