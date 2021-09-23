package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Produto;

public interface Repository extends JpaRepository<Produto, Long>{
	List<Produto> findByNome(String nome);

}
