package com.brenodev.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<Telefone> getFones(Long id){
		return telefoneRepository.getTelefones(id);
	}
	
	public void removerPorId(Long id) {
		telefoneRepository.deleteById(id);
	}
	
	public Optional<Telefone> getAFone(Long id) {
		return telefoneRepository.findById(id);
	}
}
