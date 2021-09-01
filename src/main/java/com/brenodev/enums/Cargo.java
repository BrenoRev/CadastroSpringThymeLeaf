package com.brenodev.enums;

public enum Cargo {

	ADMIN("Administrador"),
	SENIOR("Sênior"),
	PLENO("Pleno"),
	JUNIOR("Junior"),
	ESTAGIARIO("Estagiario"),
	TRAINEE("Trainee");
	
	private String nome;
	
	private Cargo(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
