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

import br.com.model.Pc;
import br.com.repository.Repository;

@org.springframework.stereotype.Controller
//@RequestMapping(value="/produtos",method= RequestMethod.GET)
@RequestMapping("/pcs/")

public class Controller {

	@Autowired
	private Repository reposit;

	@GetMapping("inicio")
	public String inicio() {
		return "index";
	}

	@GetMapping("showform")
	public String showPcForm(Pc pc) {
		return "add-pc";
	}

	@GetMapping("/list")
	public String pcs(Model model) {
		model.addAttribute("pcs", this.reposit.findAll());
		return "list";
	}

	@PostMapping("/add")
	public String addPc(@Validated Pc pc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-pc";
		}
		this.reposit.save(pc);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Pc pc = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(" pc invalido" + id));
		model.addAttribute("pc", pc);
		return "update";
	}

	@GetMapping("/del/{id}")
	public String deletaPc(@PathVariable("id") long id, Model model) {
		Pc pc = this.reposit.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Este PC não está aqui." + id));

		this.reposit.delete(pc);
		model.addAttribute("pc", this.reposit.findAll());
		return "list";
	}

	@PostMapping("atualiza/{id}")
	public String atualizaPc(@PathVariable("id") long id, Pc pc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			pc.setId(id);
			return "update";
		}
		// atualiza produto
		reposit.save(pc);

		// pega todos os produtos atualizados
		model.addAttribute("pcs", this.reposit.findAll());
		return "list";

	}
}
