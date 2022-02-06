package br.com.alura.budgetManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.alura.budgetManagement.entity.Role;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.repository.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

	private RoleRepository roleRepository;

	@Override
	public List<Role> addRoles(List<Role> values) throws BusinessException {
		Optional<Role> role = this.roleRepository.findAll().stream().findAny();
		
		if (role.isPresent()) 
			throw new BusinessException("Table ROLE has already been populated.");
		
		return this.roleRepository.saveAll(values);
	}

}
