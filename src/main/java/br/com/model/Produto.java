package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "fabricante")
	private String fabricante;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "precoEntrada")
	private double precoEntrada;

	@Column(name = "precoSaida")
	private double precoSaida;
	
	

}
