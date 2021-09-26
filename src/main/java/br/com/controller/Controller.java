package br.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.model.Produto;
import br.com.repository.Repository;

@org.springframework.stereotype.Controller
//@RequestMapping(value="/produtos",method= RequestMethod.GET)
@RequestMapping("/produtos/")

public class Controller {

	@Autowired
	private Repository reposit;

	@GetMapping("showform")
	public String showProdutoForm(Produto produto) {
		return "add-produto";
	}

	@GetMapping("/list")
	public String produtos(Model model) {
		model.addAttribute("produtos", this.reposit.findAll());
		return "index";
	}

	@PostMapping("/add")
	public String addProtudo(@Validated Produto produto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-produto";
		}
		this.reposit.save(produto);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Produto produto = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(" produto invalido" + id));
		model.addAttribute("produto", produto);
		return "update";
	}

	@GetMapping("/del/{id}")
	public String deleta(@PathVariable("id") long id, Model model) {
		Produto produto = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("produto nao existe" + id));

		this.reposit.delete(produto);
		model.addAttribute("produto", this.reposit.findAll());
		return "index";
	}

	@PostMapping("atualiza/{id}")
	public String atualizaProduto(@PathVariable("id") long id, Produto produto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			produto.setId(id);
			return "update";
		}
		// atualiza produto
		reposit.save(produto);

		// pega todos os produtos atualizados
		model.addAttribute("produtos", this.reposit.findAll());
		return "index";

	}
}
