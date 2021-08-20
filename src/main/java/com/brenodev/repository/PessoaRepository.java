package com.brenodev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "SELECT p FROM Pessoa p WHERE p.nome LIKE %?1%")
	public List<Pessoa> pesquisarNome(String nome);
	
}
