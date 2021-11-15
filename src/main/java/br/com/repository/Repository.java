package br.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.model.Estudante;

public interface Repository extends JpaRepository<Estudante, Long> {

	@Query("select p from Estudante p where p.aluno like %?1%")
	List<Estudante> findByNome(String aluno);

	
	@Query("select p from Estudante p where p.matricula like %?1% group by Estudante")
	public Optional<Estudante> findByMatricula(String matricula);

	@Query ("select sum(p.horas) from Estudante p where p.matricula like %?1%")
	public Optional<Estudante> somahoras(String totalHoras);
	

	/*  FUNCIONANDO CONSULTA POR MATRICULA
	@Query("select p from Estudante p where p.matricula like %?1% group by Estudante")
	public Optional<Estudante> findByMatricula(String matricula);*/
}