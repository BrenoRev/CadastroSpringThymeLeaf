package com.brenodev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenodev.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	TelefoneRepository telefoneRepository;
}
