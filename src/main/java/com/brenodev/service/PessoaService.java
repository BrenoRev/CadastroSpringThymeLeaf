package com.brenodev.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		// Configurando a pesquisa para consultar por partes do nome no banco de dados, igual ao like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		// Une o objeto com o valor a configuração para consultar
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = pessoaRepository.findAll(example, pageable);
		
		return pessoas;
	}
}
