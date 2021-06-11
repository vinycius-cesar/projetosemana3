package com.projetosemana3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetosemana3.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//Validar se login ja está criado
	@Query(value = "SELECT count(*) from usuario where login = ?1", nativeQuery = true)
	public Long validarLogin(String login);
	
	
	//Validar se login ja está criado
		@Query(value = "SELECT from usuario where login = ?1", nativeQuery = true)
		public Long validarLoginOK(String login);
	
	
	
	
	//Verificar se o login existe
	@Query(value = "SELECT login, senha, status from usuario where login = ?1 and senha = ?2", nativeQuery = true)
	public Long verificarLogin(String login, String senha);
	
	
	//PEGAR O STATUS DA CONTA
	@Query(value = "SELECT status from usuario where login = ? and senha = ?", nativeQuery = true)
	public String verificarStatus(String login, String senha);

}
