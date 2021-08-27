package com.brenodev.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	private String login;
	
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER)
	/* 
 	VAI CRIAR UMA TABELA COM O NOME usuarios_role 
	QUE VAI TER UMA COLUMA CHAMADA "usuario_id" referenciando o "id" da tabela "usuario"
	E UMA COLUNA CHAMADA "role_id" QUE REFERENCIA O "id" DA TABELA "role" 
	*/
	@JoinTable // Cria uma tabela de acesso do usuário
	(name = "usuarios_role",
	joinColumns = @JoinColumn(name = "usuario_id", 
	referencedColumnName = "id", 
	table = "usuario"),
	
	inverseJoinColumns = @JoinColumn(name = "role_id",
	referencedColumnName = "id",
	table = "role") )
	
	private List<Role> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
