package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "estudante")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "aluno")
	private String aluno;

	@Lob
	@Column(name = "nomeDocumento")
	private String nomeDocumento;

	@Column(name = "atividadeCertificado")
	private String atividadeCertificado;

	@Column(name = "horas")
	private String horas;

	@Column(name = "homologado")
	private String homologado;

	@Column(name="naoHomologado")
	private String naoHomologado;
	
	@Column(name="periodo")
	private String periodo;
	
	

}
