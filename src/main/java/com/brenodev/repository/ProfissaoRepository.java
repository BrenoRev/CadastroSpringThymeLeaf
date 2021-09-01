package com.brenodev.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Profissao;

@Repository
@Transactional
public interface ProfissaoRepository extends JpaRepository<Profissao, Long>{

}
