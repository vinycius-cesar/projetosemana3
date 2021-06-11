package com.projetosemana3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetosemana3.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente>findByNomeContains(String name);
	
//	List<Cliente>findByNomeAndEmail(String name, String email);
	

}
