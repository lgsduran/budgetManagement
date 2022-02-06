package br.com.alura.budgetManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.budgetManagement.entity.Role;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.service.RoleServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	private RoleServiceImpl roleService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Role> addRoles(@Valid @RequestBody List<Role> role) throws BusinessException {
		return this.roleService.addRoles(role);
	}

}
