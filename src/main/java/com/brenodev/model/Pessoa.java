package com.brenodev.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.brenodev.enums.Cargo;

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
	
	private String sexo;
	
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Lob
	private byte[] curriculo;
			
	@ManyToOne
	private Profissao profissao;
	
	@OneToMany(mappedBy="pessoa", orphanRemoval = false, cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Cep cep;
}
