package com.brenodev.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenodev.model.Pessoa;
import com.brenodev.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService{
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public void salvarPessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> buscarPorID(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	public List<Pessoa> buscarTodos(){
		return pessoaRepository.findAll();
	}
	
	public List<Pessoa> findPessoaByName(String nome){
		return pessoaRepository.pesquisarNome(nome.toUpperCase());
	}
	
}
