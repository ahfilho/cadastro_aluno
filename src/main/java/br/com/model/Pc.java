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

@Table(name = "pcCliente")
public class Pc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cliente")
	private String cliente;
	
	@Column(name = "cpf")
	private int cpf;

	@Column(name = "telefone")
	private int telefone;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name="maquina")
	private String maquina;
	
	@Column(name = "fabricante")
	private String fabricante;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "problemaPc")
	private String problemaPc;

	@Column(name = "reparo")
	private String reparo;

	@Column(name = "valorReparo")
	private int valorReparo;
	

}
