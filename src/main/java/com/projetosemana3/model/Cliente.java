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
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	private Long id;
	
	private LocalDate dataCadastro;
	
	
	@NotEmpty(message = "O NOME não pode estar VAZIO")
	@NotNull(message = "O NOME não pode ser NULL")
	@Column(length = 30)
	private String nome;
	
	
	@NotEmpty(message = "O CNPJ não pode estar VAZIO")
	@NotNull(message = "O CNPJ não pode ser NULL")
	@Column(length = 14)
	private String cpfCnpj;
	
	
	@NotEmpty(message = "O LOGRADOURO não pode estar VAZIO")
	@NotNull(message = "O LOGRADOURO não pode ser NULL")
	@Column(length = 50)
	private String logradouro;
	
	
	@NotEmpty(message = "A CIDADE não pode estar VAZIA")
	@NotNull(message = "A CIDADE não pode ser NULL")
	@Column(length = 40)
	private String cidade;
	
	
	@NotEmpty(message = "O UF não pode estar VAZIO")
	@NotNull(message = "O UF não pode ser NULL")
	@Column(length = 2)
	private String uf;
	
	
	@NotEmpty(message = "O CEP não pode estar VAZIO")
	@NotNull(message = "O CEP não pode ser NULL")
	@Column(length = 8)
	private String cep;
	
	
	@Column(length = 11)
	private String telefone;
	
	
	@Column(length = 100)
	private String email;

	


}
