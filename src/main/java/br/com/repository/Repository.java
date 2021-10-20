package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Estudante;

public interface Repository extends JpaRepository<Estudante, Long>{
	//List<Estudante> findByEstudante(String usuario);
}
