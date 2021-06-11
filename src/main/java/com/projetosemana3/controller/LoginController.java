package com.projetosemana3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosemana3.model.Usuario;
import com.projetosemana3.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@PostMapping("/")
	public String verificarLoginOK(@RequestBody Usuario usuario){
		return this.loginService.verficarLogin(usuario);
	}

}
