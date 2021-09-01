package com.brenodev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="cep")
	private List<Pessoa> pessoas;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	private String cep;
	
	private String rua;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private String ibge;

	@Override
	public String toString() {
		return "Cep [bairro=" + bairro + ", cidade=" + cidade + "]";
	}
	
	
}
