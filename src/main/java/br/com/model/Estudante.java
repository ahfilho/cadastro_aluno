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

	public Estudante(String fileName, String contentType, byte[] bytes) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "aluno")
	private String aluno;

	@Lob
	@Column( name = "content")
	private byte [] content;
	
	@Column(name = "docName")
	private String docName;
	
	@Column(name = "tamanho")
	private long tamanho;

	@Column(name = "atividadeCertificado")
	private String atividadeCertificado;

	@Column(name = "horas")
	private String horas;
	

	@Column(name = "totalhoras")
	private String totalHoras;

	@Column(name = "homologado")
	private String homologado;

	@Column(name="naoHomologado")
	private String naoHomologado;
	
	@Column(name="periodo")
	private String periodo;
	
	

}
