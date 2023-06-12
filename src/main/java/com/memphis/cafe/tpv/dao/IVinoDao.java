package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Vino;

public interface IVinoDao extends CrudRepository<Vino, Integer>{

	@Query(value = "select c from #{#entityName} c")
	public List<Vino> listarVinos();
	
	@Query(value = "SELECT c.precioBotella FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioVino(String nombre);
	
	@Query(value = "SELECT c.precioCopa FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioCopaVino(String nombre);
	
	
}
