package com.projetosemana3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.projetosemana3.model.Usuario;
import com.projetosemana3.service.UsuarioService;
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//MÉTODO PARA BUSUCAR USUARIO
	@GetMapping("/")
	public List<Usuario> buscarClientesFiltro(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "email", required = false) String email){
		
		return this.usuarioService.buscarUsuariosFiltro(nome, email);
	}

	
	
	//MÉTODO PARA SALVAR USUARIO
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public String salvarUsuario(@RequestBody @Valid Usuario usuario) {
		return this.usuarioService.salvarUsuario(usuario);
		
	}
	
	
	//MÉTODO PARA BUSCAR USUARIO POR ID
	@GetMapping("/{id}")
	public Usuario buscarUsuarioID(@PathVariable("id") Long id) {
		return this.usuarioService.buscarUsuarioPorID(id);
	}
	
	
	//MÉTODO PARA ATUALIZAR USUARIO
	@PutMapping("/{id}")
	public String alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return this.usuarioService.atualizarUsuario(usuario, id);
		
	}
	
	
	//MÉTODO PARA EXCLUIR O USUARIO
	@DeleteMapping("/{id}")
	public String excluirUsuario(@PathVariable("id") Long id) {
		return this.usuarioService.excluirUsuario(id);
		
	}
	
}
