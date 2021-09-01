package com.brenodev.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Telefone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@Length(min = 8)
	private String numero;
	
	@Length(min = 4)
	private String tipo;
	
	@ManyToOne
	private Pessoa pessoa;

	@Override
	public String toString() {
		return "Telefone [numero=" + numero + ", tipo=" + tipo + "]";
	}
	
	
}
