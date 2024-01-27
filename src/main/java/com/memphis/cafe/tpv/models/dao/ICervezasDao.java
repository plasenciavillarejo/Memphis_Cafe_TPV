package com.memphis.cafe.tpv.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.models.entity.Cerveza;
import com.memphis.cafe.tpv.models.entity.CervezaBarril;

public interface ICervezasDao extends CrudRepository<Cerveza, Integer>{

	@Query(value = "select c from #{#entityName} c")
	public List<Cerveza> listaCervezas();
	
	@Query(value = "SELECT c.precio FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioCervezas(String nombre);
		
}
