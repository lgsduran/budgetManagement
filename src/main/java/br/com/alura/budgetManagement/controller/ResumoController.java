package br.com.alura.budgetManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.response.ResumoResponse;
import br.com.alura.budgetManagement.service.ResumoImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/resumo")
public class ResumoController {
	
	private ResumoImpl resumoImpl;
	
	@GetMapping("/{year}/{month}")
	public ResumoResponse generateResumo(@PathVariable int year, @PathVariable int month) throws BusinessException {
		return resumoImpl.generateResumo(year, month);
		
	}
}
