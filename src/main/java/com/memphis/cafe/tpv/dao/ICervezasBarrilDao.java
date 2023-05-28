package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.CervezaBarril;

public interface ICervezasBarrilDao extends CrudRepository<CervezaBarril, Integer>{

	@Query(value = "select c from #{#entityName} c")
	public List<CervezaBarril> listaCervezasBarril();
	
	@Query(value = "SELECT c.precio FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioCervezasBarril(String nombre);
	
}

