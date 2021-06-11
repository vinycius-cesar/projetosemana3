package com.projetosemana3.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.projetosemana3.dto.LivroCaixaDTO;
import com.projetosemana3.model.LivroCaixa;
import com.projetosemana3.service.LivroCaixaService;

@RestController
@RequestMapping(value = "/livrocaixa")
public class LivroCaixaController {
 private double saldoTotal = 0;
	@Autowired
	private LivroCaixaService livroCaixaService;
	
	
	//MÉTODO PARA SALVAR
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public String salvarLivro(@RequestBody @Valid LivroCaixa livroCaixa) {
		return this.livroCaixaService.salvarLivro(livroCaixa);
	}
	
	
	//MÉTODO PARA BUSCAR TODOS
	@GetMapping("/")
	public List<LivroCaixa> buscarLivros(Pageable pageable){
		return this.livroCaixaService.buscarLivroCaixa(pageable);
	}
	
	
	//MÉTODO PARA BUSCAR LIVRO CAIXA POR ID
	@GetMapping("/{id}")
	public  LivroCaixa buscarLivroID(@PathVariable("id") Long id) {
		return this.livroCaixaService.buscarLivroID(id);
	}
	
	
	//MÉTODO PARA ATUALIZAR LIVRO
	@PutMapping("/{id}")
	public String alterarLivro(@PathVariable("id") Long id, @RequestBody LivroCaixa livroCaixa) {
		return this.livroCaixaService.atualizarLivroCaixa(livroCaixa, id);
	}
	
	
	//MÉTODO PARA EXCLUIR O LIVRO CAIXA
	@DeleteMapping("/{id}")
	public String excluirLivro(@PathVariable("id") Long id) {
		return this.livroCaixaService.excluirLivroCaixa(id);
	}
	
	
	//MÉTODO BUSCAR LIVRO CAIXA POR ID CLIENTE
	@GetMapping("/relatorio")
	public List<LivroCaixa> buscarLivroPorID(@RequestParam("idcliente") Long idCliente){
		return this.livroCaixaService.buscarLivroClienteID(idCliente);
	}
	
	
	// MÉTODO PARA BUSCAR RELATORIO POR DATAS
	@GetMapping("/datalancamento")
	public List<LivroCaixaDTO> buscarPorData(@RequestParam("datainicio") String dataInicio,
			@RequestParam("datafinal") String dataFinal, @RequestParam("idcliente") Long idCliente) {
		saldoTotal = 0;
		return livroCaixaService.relatorioDatas(dataInicio, dataFinal, idCliente)
				.stream()
				.map(this::toLivroCaixa)
				.collect(Collectors.toList());
	}
	
	//DTO LIVRO CAIXA DTO
	 public LivroCaixaDTO toLivroCaixa(LivroCaixa livroCaixa) {
	    		    	
	    	LivroCaixaDTO dto = new LivroCaixaDTO();
	    	dto.setIdCliente(livroCaixa.getCliente().getId());
	    	dto.setCpfCnpjf(livroCaixa.getCliente().getCpfCnpj());
	    	dto.setTelefone(livroCaixa.getCliente().getTelefone());
	    	dto.setDataLancamento(livroCaixa.getDataLancamento());
	    	dto.setDescricao(livroCaixa.getDescricao());
	    	dto.setDescricao(livroCaixa.getDescricao());
	    	dto.setTipo(livroCaixa.getTipo());
	    	dto.setValor(livroCaixa.getValor());
	    	saldoTotal += livroCaixa.getValor();
	    	dto.setSaldo(saldoTotal);
	    	
	    	return dto;
	    }
		
	
}
