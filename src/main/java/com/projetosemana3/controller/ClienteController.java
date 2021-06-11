package com.projetosemana3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.projetosemana3.model.Cliente;
import com.projetosemana3.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//MÉTODO PARA SALVAR CLIENTE
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public String salvarCliente(@RequestBody @Valid Cliente cliente) {
		return this.clienteService.salvarCliente(cliente);
	}
	
	//MÉTODO PARA BUSCAR CLIENTE POR ID
	@GetMapping("/{id}")
	public Cliente buscarClienteID(@PathVariable("id") Long id) {
		return this.clienteService.buscarClienteID(id);
	}
	
	
	//MÉTODO LISTANDO CLIENTES - COM FILTROS
	@GetMapping("/")
	public List<Cliente> buscarClientesFiltro(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
			@RequestParam(value = "cidade", required = false) String cidade,
			@RequestParam(value = "uf", required = false) String uf) {
		return this.clienteService.buscarClientesFiltro(nome, cpfCnpj, cidade, uf);

	}
	
	
	//MÉTODO PARA EXCLUIR CLIENTE
	@DeleteMapping("/{id}")
	public String excluirCliente(@PathVariable Long id) {
		return this.clienteService.excluirCliente(id);
	}
	
	
	//MÉTODO PARA ATUALIZAR O CLIENTE
	@PutMapping("/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		
		return this.clienteService.atualizarCliente(cliente, id);
	}
	
	
	

}
