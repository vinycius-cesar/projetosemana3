package com.projetosemana3.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LivroCaixaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idCliente;
	private String cpfCnpjf;
	private String telefone;
	private String dataLancamento;
	private String descricao;
	private String tipo;
	private double valor;
	private double saldo;

	
	
	

}
