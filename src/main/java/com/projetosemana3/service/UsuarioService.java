package com.projetosemana3.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetosemana3.exception.BadRequestException;
import com.projetosemana3.model.Usuario;
import com.projetosemana3.repository.UsuarioCustomRepository;
import com.projetosemana3.repository.UsuarioRepository;
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioCustomRepository usuarioCustomRepository;
	
	
	//MÉTODO PARA BUSCAR USUARIO - FILTRO POR NOME OU EMAIL
	public List<Usuario> buscarUsuariosFiltro(String nome, String email){
		return this.usuarioCustomRepository.findUsuario(nome, email);
	}
		

	
	//MÉTODO PARA SALVA USUARIO
	public String salvarUsuario(Usuario usuario) {
			
		String checkLogin = usuario.getLogin();
		Long checkLogin2 = usuarioRepository.validarLogin(checkLogin);
				
		if(checkLogin2 > 0) {
			throw new BadRequestException("USUARIO: "+ checkLogin + " já EXISTE!");
		}if(!usuario.getPerfil().equals("a") && !usuario.getPerfil().equals("0")) {
			return "O PERFIL informado não é valido \n Informe: (A) para ADMINISTRADOR e (0) para OPERADOR";
		
		}if(!usuario.getStatus().equals("a") && !usuario.getStatus().equals("c")) {
			return "O STATUS informado não é valido \n Informe: (A) para ATIVO e (C) para CANCELADO";
		
		}if(usuario.getDataCadastro() == null) {
			usuario.setDataCadastro(LocalDate.now());
		}
		
						
		this.usuarioRepository.save(usuario);
		return "Usuario salvo com sucesso";
	}
	
	//MÉTODO PARA BUSCAR USUARIO POR ID
	public Usuario buscarUsuarioPorID(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new BadRequestException("Usuario não encontrado"));		
	}
	
	
	//MÉTODO PARA ATUALIZAR O USUARIO
	public String atualizarUsuario(Usuario usuario, Long id) {
		Usuario usuarioBD = this.usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException("USUARIO não encontrado, não é possivel fazer alteração"));		
		BeanUtils.copyProperties(usuario, usuarioBD, "id");
		this.salvarUsuario(usuarioBD);
		return "Usuario de ID "+ id + " Atualizado com sucesso!";
	}
	
	
	//MÉTODO PARA EXCLUIR O USUARIO
	public String excluirUsuario(Long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario de ID: " +id+ " EXCLUIDO com sucesso!";
	}
	
	

	
	
}
