package br.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.model.Estudante;

public interface Repository extends JpaRepository<Estudante, Long> {
	
	
	
	@Query("select p from Estudante p where p.aluno like %?1%")
	List<Estudante> findByNome(String aluno);
	
	//@Query("select q from Estudante q where q.id like %?1%")
	//List<Estudante> contadorHoras(String horas);
	
	//@Query("select p from Estudante p where p.id =")
	//Optional<Estudante> findById(Long id );
	
	//@Query("select aluno from estudante where horas >= 20")
	//public String valida(String horas);
	
	// realiza soma de uma coluna de acordo com o que busco
	//@Query("select sum(horas) from estudante where aluno = Arlindo jose de lima filho")
	//public String valida(String horas);

}
