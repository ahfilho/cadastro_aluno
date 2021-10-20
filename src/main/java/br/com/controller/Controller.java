package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.model.Estudante;
import br.com.repository.Repository;

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
	public String addPc(@Validated Estudante estudante, BindingResult result, Model model) {
	estudante.getUsuario();
		if (result.hasErrors()) {
			return "add-pc";
}
		this.reposit.save(estudante);
		return "redirect:list";
	}
	@GetMapping("pesquisa")
	public String pesquisa() {
		return "pesquisaId";
	}

	@GetMapping("pesquisaId/{id}")
	public String pesquisaId(@PathVariable("id") long id, Model model) {
		Estudante estudante = this.reposit.findById(id).orElseThrow(() -> new IllegalArgumentException("ID NÃO ENCONTRADO"+id));
				model.addAttribute("estudante",estudante);
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
