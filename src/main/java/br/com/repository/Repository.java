package br.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.model.Estudante;

public interface Repository extends JpaRepository<Estudante, Long> {
	
	
	
	@Query("select p from Estudante p where p.aluno like %?1%")
	List<Estudante> findByNome(String aluno);
	


}
