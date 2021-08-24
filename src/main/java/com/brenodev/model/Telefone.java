package com.brenodev.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Telefone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@NotEmpty(message="Número não pode ser vazio")
	@NotNull(message="Número não pode ser nulo")
	private String numero;
	
	@NotEmpty(message="Número não pode ser vazio")
	@NotNull(message="Tipo não pode ser nulo")
	private String tipo;
	
	@ManyToOne
	private Pessoa pessoa;
}
