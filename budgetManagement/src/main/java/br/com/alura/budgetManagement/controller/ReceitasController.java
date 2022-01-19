package br.com.alura.budgetManagement.controller;

import org.springframework.web.bind.annotation.*;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.AddReceitaRequest;
import br.com.alura.budgetManagement.request.AlterReceitaRequest;
import br.com.alura.budgetManagement.response.Response;
import br.com.alura.budgetManagement.service.ReceitasServiceImpl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {
	
	private ReceitasServiceImpl receitasService;

	public ReceitasController(ReceitasServiceImpl receitasService) {
		this.receitasService = receitasService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Receitas	addReceitas(@Valid @RequestBody AddReceitaRequest request) 
			throws BusinessException {
		return this.receitasService.addReceita(request);		
	}
	
	@GetMapping
	public Page<Receitas> listReceitas(@Valid  @PageableDefault Pageable pageable) {
		return this.receitasService.listReceitas(pageable);
	}
	
	@GetMapping("/{id}")
	public Receitas getReceitaById(@Valid  @PathVariable long id) throws BusinessException {
		return this.receitasService.getReceitaById(id);
	}
	
	@PutMapping("/{id}")
    public Receitas alterOpponent(@PathVariable Long id,
                                  @RequestBody AlterReceitaRequest request) throws BusinessException {
        return this.receitasService.alterReceita(id, request);
    }
	
	@DeleteMapping("/{id}")
	public Response deleteReceita(@PathVariable long id) throws BusinessException {
		return this.receitasService.deleteReceita(id);

	}
	

}
