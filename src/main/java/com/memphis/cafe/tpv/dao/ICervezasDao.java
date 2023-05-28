package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Cerveza;

public interface ICervezasDao extends CrudRepository<Cerveza, Integer>{

	@Query(value = "select c from #{#entityName} c")
	public List<Cerveza> listaCervezas();
	
	@Query(value = "SELECT c.precio FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioCervezas(String nombre);
}
