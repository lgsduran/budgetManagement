package br.com.alura.budgetManagement.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.budgetManagement.entity.Receitas;
import br.com.alura.budgetManagement.exception.BusinessException;
import br.com.alura.budgetManagement.request.AddReceitaRequest;
import br.com.alura.budgetManagement.request.AlterReceitaRequest;
import br.com.alura.budgetManagement.response.Response;
import br.com.alura.budgetManagement.service.ReceitasServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/receitas")
@SecurityRequirement(name = "bearerAuth")
public class ReceitasController {
	
	private ReceitasServiceImpl receitasService;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Receitas	addReceitas(@Valid @RequestBody AddReceitaRequest request) 
			throws BusinessException {
		return this.receitasService.addReceita(request);		
	}
	
	
	@RequestMapping(value = "/descricao", method = GET)
	public List<Receitas> listReceitasByDescricao(@Valid  @RequestParam("descricao") 
			String descricao) throws BusinessException {
		return this.receitasService.listReceitasByDescricao(descricao);
	}
	
	@GetMapping("/{year}/{month}")
	public List<Receitas> listReceitasByYearMonth(@Valid  @PathVariable 
			int year, @PathVariable int month) throws BusinessException {
		return this.receitasService.listReceitasByYearMonth(year, month);
	}
	
	@GetMapping
	public Page<Receitas> listReceitas(@Valid  @ParameterObject @PageableDefault Pageable pageable) {
		return this.receitasService.listReceitas(pageable);
	}
	
	@GetMapping("/{id}")
	public Receitas getReceitaById(@Valid  @PathVariable long id) 
			throws BusinessException {
		return this.receitasService.getReceitaById(id);
	}
	
	@PutMapping("/{id}")
    public Receitas alterDespesas(@PathVariable Long id,
                                  @RequestBody AlterReceitaRequest request) 
                                		  throws BusinessException {
        return this.receitasService.alterReceita(id, request);
    }
	
	@DeleteMapping("/{id}")
	public Response deleteReceita(@PathVariable long id) 
			throws BusinessException {
		return this.receitasService.deleteReceita(id);

	}
	

}
