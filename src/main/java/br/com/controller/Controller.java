package br.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.ConnectionFactoryCustomizer;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.model.Estudante;
import br.com.repository.Repository;
import javassist.tools.rmi.ObjectNotFoundException;
import net.bytebuddy.asm.Advice.This;
import servico.Servico;

@org.springframework.stereotype.Controller
@RequestMapping("/estudantes/")

public class Controller {
	
	
	@Autowired
	private Repository reposit;

	@GetMapping("inicio")
	public String inicio() {
		return "index";
	}

	@GetMapping("showform")
	public String showPcForm(Estudante estudante) {
		return "add-pc";
	}

	@GetMapping("/list")
	public String listaEstudantes(Model model) {
		model.addAttribute("estudantes", this.reposit.findAll());

		return "list";
	}

	@PostMapping("/add")
	public String add(@RequestParam("nomeDocumento") MultipartFile nomeDocumento, @Validated Estudante estudante, BindingResult result, Model model) throws IOException {
		estudante.getAluno();
		if (result.hasErrors()) {
			return "add-pc";
		}
		 String fileName = nomeDocumento.getOriginalFilename();
		 estudante.setDocName(fileName);
		 estudante.setContent(nomeDocumento.getBytes()); 
		 estudante.setTamanho(nomeDocumento.getSize());
		 
		this.reposit.save(estudante);
		return "redirect:espera";
	}
	@GetMapping("/downloadfile")
	public void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws IOException{
		Optional<Estudante> temp = reposit.findById(id);
		if(temp != null) {
			Estudante estudante = temp.get();
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
				String headerValue = "attachment; filename = "+ estudante.getDocName();
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outputStrream = response.getOutputStream();
				outputStrream.write(estudante.getContent());
				outputStrream.close();
		}
	}
	@GetMapping("/doc")
	public void showDoc(@Param("id") Long id, HttpServletResponse response, Optional<Estudante> estudante)
			   throws ServletException, IOException {
			  
			  estudante = reposit.findById(id);
			  response.setContentType("doc/pdf, doc/txt");
			  response.getOutputStream().write(estudante.get().getContent());
			  response.getOutputStream().close();
			 }
			
	//PESQUISA POR NOME
	@PostMapping("/pesquisa")
	public String pesquisar(@RequestParam("idpesquisa") String idpesquisa, Model model) {

		model.addAttribute("estudantes", this.reposit.findByNome(idpesquisa));
		return "pesquisaId";
	}
	@PostMapping("/pesquisaId")
	public String pesquisaId(@RequestParam("pesquisaporid") Long pesquisaporid, Model model) {
		model.addAttribute("estudantes", this.reposit.findById(pesquisaporid));
		return "pesquisaId";
		
	}
	
	@GetMapping("espera")
	public String espera() {
		return "espera";

	}

	@GetMapping("pesquisaid")
	public String pesquisa() {
		return "pesquisaId";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Estudante estudante = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(" estudante invalido" + id));
		model.addAttribute("estudante", estudante);
		return "update";
	}

	@GetMapping("/del/{id}")
	public String deletaPc(@PathVariable("id") long id, Model model) {
		Estudante estudante = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Este estudante não está aqui." + id));

		this.reposit.delete(estudante);
		model.addAttribute("estudante", this.reposit.findAll());
		return "list";
	}

	@PostMapping("atualiza/{id}")
	public String atualizaPc(@PathVariable("id") long id, Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			estudante.setId(id);
			return "update";
		}
		// atualiza produto
		reposit.save(estudante);

		// pega todos os produtos atualizados
		model.addAttribute("estudantes", this.reposit.findAll());
		return "list";
	}
	
		
	

}
