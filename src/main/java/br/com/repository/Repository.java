package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Pc;

public interface Repository extends JpaRepository<Pc, Long>{
	List<Pc> findByCliente(String cliente);

}
