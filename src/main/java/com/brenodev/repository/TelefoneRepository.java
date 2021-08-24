package com.brenodev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	@Query(value="SELECT t FROM Telefone t where t.pessoa.id = ?1")
	public List<Telefone> getTelefones(Long pessoaid);
	
}
