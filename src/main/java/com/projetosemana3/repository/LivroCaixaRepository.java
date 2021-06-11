package com.projetosemana3.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetosemana3.model.LivroCaixa;

@Repository
public interface LivroCaixaRepository extends JpaRepository<LivroCaixa, Long> {
	
	@Query(value = "SELECT L.id, L.data_lancamento, L.descricao, L.tipo, L.valor, L.id_cliente\r\n"
			+ "FROM livro_caixa AS L\r\n"
			+ "INNER JOIN cliente\r\n"
			+ "ON L.id_cliente = cliente.id\r\n"
			+ "where cliente.id = ?1", nativeQuery = true)
	public List<LivroCaixa> buscarLivroClienteID(Long clienteId);
	
	
	//BUSCAR LIVROS POR DATA
	@Query(value = "select * from livro_caixa where data_lancamento BETWEEN ?1 AND ?2 AND id_cliente= ?3 ", nativeQuery = true)
	public List<LivroCaixa> relatorioDatas( String dataInicio, String dataFinal, Long idCliente);
	
	
	/*
	@Query(value = "SELECT *,(SELECT SUM(valor) from livro_caixa where id_cliente = ?) FROM livro_caixa", nativeQuery = true)
	public BigDecimal checkSaldo(Long idCliente);*/
	
	
	

}
