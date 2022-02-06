package br.com.alura.budgetManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.budgetManagement.entity.Role;
import br.com.alura.budgetManagement.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleType name);

}
