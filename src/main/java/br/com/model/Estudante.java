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

@Table(name = "estudante")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "nomeDocumento")
	private String nomeDocumento;

	@Column(name = "atividadeCertificado")
	private String atividadeCertificado;
	
	@Column(name = "horas")
	private String horas;
	
	@Column(name="status")
	private String status;
	
}
