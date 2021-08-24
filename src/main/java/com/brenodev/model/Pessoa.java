package com.brenodev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@NotNull
	@NotBlank
	@Length(min=3, max= 15)
	private String nome;
	
	@NotNull
	@NotBlank
	@Length(min=3, max= 15)
	private String sobrenome;
	
	@NotNull
	@Min(1)
	@Max(100)
	private Integer idade = null;
	
	@OneToMany(mappedBy="pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telefone> telefone;
}
