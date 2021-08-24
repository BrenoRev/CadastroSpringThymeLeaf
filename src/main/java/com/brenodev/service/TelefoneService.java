package com.brenodev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenodev.model.Telefone;
import com.brenodev.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	TelefoneRepository telefoneRepository;
	
	public Telefone save(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}
}
