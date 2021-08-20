package com.brenodev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.brenodev.model.Pessoa;
import com.brenodev.service.PessoaService;



@Controller
public class IndexController {

	@Autowired	
	PessoaService pessoaService;
	
	@RequestMapping(method=RequestMethod.GET , value="**/cadastropessoa")
	public ModelAndView inicio() {
		// MOSTRAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		Optional<Pessoa> pessoa = Optional.of(new Pessoa());
		mv.addObject("pessoaobj", pessoa.get());
		mv.addObject("pessoas", lista);
		return mv;
	}
	
	@PostMapping("**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaService.salvarPessoa(pessoa);
		// ATUALIZAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		Optional<Pessoa> humano = Optional.ofNullable(new Pessoa());
		//Optional<Pessoa> humano = pessoaService.buscarPorID(pessoa.getId());
		mv.addObject("pessoaobj", humano.get());
		mv.addObject("pessoas", lista);
		return mv;
	}

	
	@GetMapping("**/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorID(idpessoa);
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		mv.addObject("pessoaobj", pessoa.get());
		List<Pessoa> lista = pessoaService.listarPessoas();
		mv.addObject("pessoas", lista);
		return mv;
	}
	
	@GetMapping("**/removerpessoa/{idpessoa}")
	public ModelAndView remover(@PathVariable("idpessoa") Long idpessoa) {
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		pessoaService.removerPorId(idpessoa);
		mv.addObject("pessoaobj", new Pessoa());
		mv.addObject("pessoas", pessoaService.buscarTodos());
		return mv;
	}
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam(name = "nomepesquisa") String nomepesquisa) {
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		mv.addObject("pessoas", pessoaService.findPessoaByName(nomepesquisa));
		mv.addObject("pessoaobj", new Pessoa());
		return mv;
	}
	
	@GetMapping("**/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorID(idpessoa);
		ModelAndView mv = new ModelAndView("cadastro/telefones");
		mv.addObject("pessoaobj", pessoa.get());
		return mv;
	}
}

