package com.projetosemana3.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.projetosemana3.model.*;
@Repository
public class ClienteCustomRepository {
	
	private final EntityManager em;
	
	public ClienteCustomRepository(EntityManager em) {
		this.em = em;
	}

	public List<Cliente> find(String nome, String cpfCnpj, String cidade, String uf){
		
		String query = "SELECT C from Cliente as C ";
		String condicao = "where";
		
		if(nome != null) {
			query += condicao + " C.nome = :nome";
			condicao = " and ";
		
		}if(cpfCnpj != null) {
			query += condicao + " C.cpfCnpj = :cpfCnpj";
			condicao = " and ";
		
		}if(cidade != null) {
			query += condicao + " C.cidade = :cidade";
			condicao = " and ";
		
		}if(uf != null) {
			query += condicao + " C.uf = :uf";
		}
		
		TypedQuery<Cliente> q = em.createQuery(query, Cliente.class);
		
		if(nome != null) {
			q.setParameter("nome", nome);
		
		}if(cpfCnpj != null) {
			q.setParameter("cpfCnpj", cpfCnpj);
		
		}if(cidade != null) {
			q.setParameter("cidade", cidade);
		
		}if(uf != null) {
			q.setParameter("uf", uf);
		}
		
		
				
		return q.getResultList();
	}
}
