package com.brenodev.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Cargo {

	ADMIN("Administrador"),
	GERENTE("Gerente"),
	CAIXA("Caixa"),
	REPOSITOR("Repositor");
	
	private String nome;
	
	private Cargo(String nome) {
		this.nome = nome;
	}
	
}
