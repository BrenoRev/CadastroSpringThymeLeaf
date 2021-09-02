package com.brenodev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.brenodev.model.Pessoa;
import com.brenodev.model.Telefone;
import com.brenodev.repository.CepRepository;
import com.brenodev.repository.ProfissaoRepository;
import com.brenodev.service.PessoaService;
import com.brenodev.service.TelefoneService;



@Controller
public class IndexController {

	@Autowired	
	private PessoaService pessoaService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private CepRepository cepRepository;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private ProfissaoRepository profissaoRepository;
	
	@RequestMapping(method=RequestMethod.GET , value="**/cadastropessoa")
	public ModelAndView inicio() {
		// MOSTRAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		Optional<Pessoa> pessoa = Optional.of(new Pessoa());
		mv.addObject("profissoes", profissaoRepository.findAll());
		mv.addObject("pessoaobj", pessoa.get());
		mv.addObject("pessoas", lista);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa", consumes = {"multipart/form-data"})
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult, final MultipartFile file) throws IOException {
		
		pessoa.setTelefone(telefoneService.getFones(pessoa.getId()));
		
		if(bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("cadastro/cadastropessoa");
			List<Pessoa> lista = pessoaService.listarPessoas();
			model.addObject("pessoaobj", pessoa);
			model.addObject("pessoas", lista);
			List<String> msg = new ArrayList<String>();
			
			// VAI ADICIONAR TODOS OS ERROS NA LISTA
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // VEM DAS ANOTAÇÕES
			}
			model.addObject("msg", msg);
			model.addObject("profissoes", profissaoRepository.findAll());
			return model;
		}
		// Se existir arquivo para upload
		if(file.getSize() > 0) {
			pessoa.setCurriculo(file.getBytes());
		}
		pessoaService.salvarPessoa(pessoa);
		// ATUALIZAR A LISTA
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> lista = pessoaService.listarPessoas();
		Optional<Pessoa> humano = Optional.ofNullable(new Pessoa());
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
		mv.addObject("profissoes", profissaoRepository.findAll());
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
		mv.addObject("tel	efones", telefoneService.getFones(idpessoa));
		return mv;
	}
	
	@PostMapping("**/addfonePessoa/{pessoaid}")
	public ModelAndView addFonePessoa(@Valid Telefone telefone, @PathVariable("pessoaid") Long pessoaid) {
		Pessoa pessoa = pessoaService.buscarPorID(pessoaid).get();
		
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		telefone.setPessoa(pessoa);
		telefoneService.save(telefone);
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("telefones", telefoneService.getFones(pessoaid));
		return modelAndView;
	}
	
	
	@GetMapping("**/removertelefone/{idtelefone}")
	public ModelAndView removerTelefone(@PathVariable("idtelefone") Long idtelefone) {
		Pessoa pessoa = telefoneService.getAFone(idtelefone).get().getPessoa();
		telefoneService.removerPorId(idtelefone);
		
		ModelAndView mv = new ModelAndView("cadastro/telefones");
		mv.addObject("pessoaobj", pessoa);
		mv.addObject("telefones", telefoneService.getFones(pessoa.getId()));
		return mv;
	}
	
	@GetMapping("**/pesquisarpessoa")
	public void imprimePdf(@RequestParam("nomepesquisa") String nomepesquisa,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
			
		if (nomepesquisa != null && !nomepesquisa.isEmpty()) {/*Busca somente por nome*/
			pessoas = pessoaService.findPessoaByName(nomepesquisa);
		}	
		else {	/*Busca todos*/
			
			Iterable<Pessoa> iterator = pessoaService.buscarTodos();
			for (Pessoa pessoa : iterator) {
				pessoas.add(pessoa);
			}
		}
		
		/*Chame o serviço que faz a geração do relatorio*/
		byte[] pdf = reportUtil.gerarRelatorio(pessoas, "pessoa", request.getServletContext());
		
	    /*Tamanho da resposta*/
		response.setContentLength(pdf.length);
		
		/*Definir na resposta o tipo de arquivo*/
		response.setContentType("application/octet-stream");
		
		/*Definir o cabeçalho da resposta*/
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "relatorio.pdf");
		response.setHeader(headerKey, headerValue);
		
		/*Finaliza a resposta pro navegador*/
		response.getOutputStream().write(pdf);
		
	}
}

