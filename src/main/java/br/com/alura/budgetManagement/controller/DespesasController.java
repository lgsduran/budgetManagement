package br.com.alura.budgetManagement.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.budgetManagement.entity.Despesas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.AddDespesaRequest;
import br.com.alura.budgetManagement.request.AlterDespesaRequest;
import br.com.alura.budgetManagement.response.Response;
import br.com.alura.budgetManagement.service.DespesasServiceImpl;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
	
	private DespesasServiceImpl despesasService;

	public DespesasController(DespesasServiceImpl despesasService) {
		this.despesasService = despesasService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Despesas	addDespesas(@Valid @RequestBody AddDespesaRequest request) 
			throws BusinessException {
		return this.despesasService.addDespesas(request);		
	}
	
	@GetMapping
	public Page<Despesas> listDespesas(@Valid  @PageableDefault Pageable pageable) {
		return this.despesasService.listDespesas(pageable);
	}
	
	@GetMapping("/{id}")
	public Despesas getDespesaById(@Valid  @PathVariable long id) 
			throws BusinessException {
		return this.despesasService.getDespesasById(id);
	}
	
	@PutMapping("/{id}")
    public Despesas alterDespesas(@PathVariable Long id,
                                  @RequestBody AlterDespesaRequest request) 
                                		  throws BusinessException {
        return this.despesasService.alterDespesa(id, request);
    }
	
	@DeleteMapping("/{id}")
	public Response deleteReceita(@PathVariable long id) 
			throws BusinessException {
		return this.despesasService.deleteDespesa(id);

	}
	

}