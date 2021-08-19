package com.brenodev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenodev.model.Pessoa;
import com.brenodev.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService{
	
	@Autowired
	PessoaRepository Pessoarepository;
	
	public void salvarPessoa(Pessoa pessoa) {
		Pessoarepository.save(pessoa);
	}
	
}
