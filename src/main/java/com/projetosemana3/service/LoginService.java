package com.projetosemana3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetosemana3.exception.BadRequestException;
import com.projetosemana3.model.Usuario;
import com.projetosemana3.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	//LOGIN
	 public String verficarLogin(Usuario usuario){
		 
		 String login = usuario.getLogin();
		 String senha = usuario.getSenha();
		 
		Long verificarLoginOK = usuarioRepository.verificarLogin(login, senha);
		 if(verificarLoginOK == null) {
			 throw new BadRequestException("LOGIN OU SENHA INCORRETOS!");
		 
		 }if(verificarLoginOK != null) {
			
			 String verificarStatus = usuarioRepository.verificarStatus(login, senha);
			 
			 if(!verificarStatus.equals("a")) {
				 throw new BadRequestException("USUARIO INATIVO - CANCELADO");
			 }
		 }
		 return "LOGIN REALIZADO COM SUCESSO!";
	 }

}
