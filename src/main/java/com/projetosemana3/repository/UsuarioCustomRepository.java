package com.projetosemana3.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.projetosemana3.model.Usuario;

@Repository
public class UsuarioCustomRepository {
	
	private final EntityManager em;
	
	public UsuarioCustomRepository(EntityManager em) {
		this.em = em;
	}
	
	
	public List<Usuario> findUsuario(String nome, String email){
		
		String query = "SELECT U from Usuario as U ";
		String condicao = "where";
		
		if(nome != null) {
			query += condicao + " U.nome = :nome";
			condicao = " and ";
		
		}if(email != null) {
			query += condicao + " U.email = :email";
		}
		
		TypedQuery<Usuario> q = em.createQuery(query, Usuario.class);
		
		if(nome != null) {
			q.setParameter("nome", nome);
		
		}if(email != null) {
			q.setParameter("email", email);
		}
		
		return q.getResultList();
	}
	
	

}
