package com.brenodev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
