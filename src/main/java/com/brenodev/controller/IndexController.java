package com.brenodev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brenodev.model.Pessoa;
import com.brenodev.service.PessoaService;



@Controller
public class IndexController {

	@Autowired	
	PessoaService pessoaService;
	
	@RequestMapping(method=RequestMethod.GET , value="/cadastropessoa")
	public ModelAndView inicio() {
		// MOSTRAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		mv.addObject("pessoas", lista);
		return mv;
	}
	
	@PostMapping("/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaService.salvarPessoa(pessoa);
		// ATUALIZAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		mv.addObject("pessoas", lista);
		return mv;
	}
	
}
