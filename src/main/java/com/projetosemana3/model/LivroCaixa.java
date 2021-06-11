package com.projetosemana3.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LivroCaixa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "livro_seq", sequenceName = "livro_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	
	private String dataLancamento;
	
	
	@NotEmpty(message = "A DESCRICAO não pode estar VAZIA")
	@NotNull(message = "A DESCRICAO não pode ser NULL")
	@Column(length = 50)
	private String descricao;
	
	
	@NotEmpty(message = "O TIPO não pode estar VAZIO")
	@NotNull(message = "O TIPO não pode ser NULL")
	@Column(length = 1)
	private String tipo;
	
	
	private double valor;
	
	

}
