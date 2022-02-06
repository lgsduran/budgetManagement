package br.com.alura.budgetManagement.service;

import java.util.List;

import br.com.alura.budgetManagement.entity.Role;
import br.com.alura.budgetManagement.exception.BusinessException;

public interface IRoleService {

	List<Role> addRoles(List<Role> values) throws BusinessException;

}
