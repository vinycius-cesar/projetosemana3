package com.projetosemana3.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Long id;
	
	
	private LocalDate dataCadastro;
	
	
	@Column(length = 30)
	@NotEmpty(message = "O NOME não pode estar VAZIO")
	@NotNull(message = "O NOME não pode ser NULL")
	private String nome;
	
	
	@NotEmpty(message = "O LOGIN não pode estar VAZIO")
	@NotNull(message = "O LOGIN não pode ser NULL")
	@Column(length = 15 ,unique = true)
	private String login;
	
	@Column(length = 10)
	@NotEmpty(message = "A SENHA não pode estar VAZIA")
	@NotNull(message = "O NOME não pode ser NULL")
	private String senha;
	
	
	@Column(length = 11)
	private String telefone;
	
	
	@Column(length = 100)
	private String email;
	
	
	@Column(length = 1)
	private String perfil;
	
	
	@Column(length = 1)
	private String status;

	
	
	
}
