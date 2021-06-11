package com.projetosemana3.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetosemana3.exception.BadRequestException;
import com.projetosemana3.model.Cliente;
import com.projetosemana3.model.Usuario;
import com.projetosemana3.repository.ClienteCustomRepository;
import com.projetosemana3.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteCustomRepository clienteCustomRepository;
	
	
	//MÉTODO PARA SALVAR CLIENTE
	public String salvarCliente(Cliente cliente) {
		
		//VALIDANDO O CPF		
		String cpfCnpj = cliente.getCpfCnpj();
		Pattern pattern = Pattern.compile("[0-9]{11,14}");
		Matcher matcher = pattern.matcher(cpfCnpj);
		boolean matchFound = matcher.matches();		
		
		if(matchFound == false) {
			throw new BadRequestException("CPF / CNPJ INVALIDO - Informe apenas os números (MAX: 14 carac.)");
		}
		
				
		cliente.setDataCadastro(LocalDate.now());		 
		this.clienteRepository.save(cliente);
		return "Cliente salvo com sucesso";
	}
	
	
	//MÉTODO PARA BUSUCAR CLIENTE POR ID
	public Cliente buscarClienteID(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new BadRequestException("Cliente não encontrado"));
	}
	
	
	//MÉTODO BUSCAR OS USUARIO UTILIZANDO FILTRO
		public List<Cliente> buscarClientesFiltro(String nome, String cpfCnpj, String cidade, String uf){
			return this.clienteCustomRepository.find(nome, cpfCnpj, cidade, uf);	
		}
		
	
		// MÉTODO PARA EXCLUIR CLIENTE
		public String excluirCliente(Long id) {
			this.clienteRepository.deleteById(id);
			return "Cliente de ID " + id + " Excluido com sucesso!";
		}
		
		
		//MÉTODO PARA ATUALIZAR O CLIENTE
		public String atualizarCliente(Cliente cliente, Long id) {
			Cliente clienteDB = this.clienteRepository.findById(id).orElseThrow(() -> new BadRequestException("Cliente não encontrado, não é possivel fazer alteração"));
			BeanUtils.copyProperties(cliente, clienteDB, "id");
			
			this.salvarCliente(clienteDB);
			
			return "Cliente de ID "+ id + " Atualizado com sucesso!";
		}

}
