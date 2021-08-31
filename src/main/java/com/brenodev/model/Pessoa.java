package com.brenodev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@NotEmpty(message="Nome não pode ser nulo")
	@NotNull(message="Nome não pode ser vázio")
	@Length(min=3, max= 15, message = "Nome deve ter entre 3 a 15 caracteres")
	private String nome;
	
	@NotEmpty(message="Sobrenome não pode ser nulo")
	@NotNull(message="Sobrneome não pode ser vázio")
	@Length(min=3, max= 15, message = "Sobrenome deve ter entre 3 a 15 caracteres")
	private String sobrenome;
	
	@NotNull(message = "Idade inválida")
	@Min(value= 1, message = "Idade inválida")
	@Max(value = 100, message = "Idade Invalida")
	private Integer idade;
	
	@OneToMany(mappedBy="pessoa", orphanRemoval = false, cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Cep cep;
}
