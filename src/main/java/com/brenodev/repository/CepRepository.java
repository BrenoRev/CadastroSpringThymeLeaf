package com.brenodev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long>{


}
