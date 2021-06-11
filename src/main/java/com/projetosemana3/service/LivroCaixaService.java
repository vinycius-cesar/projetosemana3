package com.projetosemana3.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetosemana3.exception.BadRequestException;
import com.projetosemana3.model.Cliente;
import com.projetosemana3.model.LivroCaixa;
import com.projetosemana3.repository.ClienteRepository;
import com.projetosemana3.repository.LivroCaixaRepository;

@Service
public class LivroCaixaService {
	
	@Autowired
	private LivroCaixaRepository livroCaixaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//MÉTODO PARA SALVAR LIVRO
	public String salvarLivro(LivroCaixa livroCaixa) {
						
		//VALIDANDO SE EXISTE CLIENTE
		Long clienteLivro = livroCaixa.getCliente().getId();
		Optional<Cliente> clienteDB = clienteRepository.findById(clienteLivro);
		if(clienteDB.isPresent() == false) {
			throw new BadRequestException("CLIENTE não encontrado, Verifique!");
		}
		
		//VALIDANDO O TIPO DE (CREDIDO OU DEBITO)
		String tipo = livroCaixa.getTipo();
		Pattern pattern = Pattern.compile("[cd]");
		Matcher matcher = pattern.matcher(tipo);
		boolean matchFound = matcher.matches();	
		if(matchFound == false) {
			throw new BadRequestException("TIPO DE MOVIMENTACAO INVALIDO - (c)PARA CREDITO - (d)PARA DEBITO ");
		
		}else if(tipo.equals("d")) {
			double valor = livroCaixa.getValor();
					
			livroCaixa.setValor(valor * -1);		
		}
						
		this.livroCaixaRepository.save(livroCaixa);
		return "Livro caixa lançado";
	}
	
	
	//MÉTODO PARA BUSCAR TODOS OS LIVROS
	public List<LivroCaixa> buscarLivroCaixa(Pageable pageable){
		return this.livroCaixaRepository.findAll(pageable).getContent();
	}
	
	
	//BUSCAR LIVRO CAIXA PELO ID
	public LivroCaixa buscarLivroID(Long id) {
		Optional<LivroCaixa> livroCaixa = livroCaixaRepository.findById(id);
		return livroCaixa.orElseThrow(() -> new BadRequestException("Livro Caixa não encontrado"));
	}
	
	
	//ALTERANDO DADOS LIVRO CAIXA
	public String atualizarLivroCaixa(LivroCaixa livroCaixa, Long id) {
		LivroCaixa livroBD = this.livroCaixaRepository.findById(id).orElseThrow(() -> new BadRequestException("Livro Caixa não encontrado, não é possivel fazer alteração"));
		BeanUtils.copyProperties(livroCaixa, livroBD, "id");
		this.salvarLivro(livroCaixa);
		
		return "Livro Caixa de ID " +id+ " ALTERADO com sucesso!";
	}
	
	
	//EXCLUINDO LIVRO CAIXA
	public String excluirLivroCaixa(Long id) {
		this.livroCaixaRepository.deleteById(id);
		return "Livro caixa de ID: " +id+ " EXCLUIDO com sucesso!";
	}
	
	
	//BUSCANDO LIVRO CAIXA POR ID DO CLIENTE
	public List<LivroCaixa> buscarLivroClienteID(Long idCliente){
		List<LivroCaixa> livroCaixa = livroCaixaRepository.buscarLivroClienteID(idCliente);
		return livroCaixa;
	}
	
	
	// MÉTODO PARA FAZER RELATORIO POR DATA
	public List<LivroCaixa> relatorioDatas(String dataInicio, String dataFinal, Long idCliente) {
		List<LivroCaixa> relatorio = livroCaixaRepository.relatorioDatas(dataInicio, dataFinal, idCliente);
	  
		return relatorio;

	}
	
	
	 /*  	
	int i = -1;
     	for(LivroCaixa teste1 : relatorio) {
     		
     		i++;
     		double teste = relatorio.get(i).getValor();
     		System.out.println(teste);
     	}*/
	

}
