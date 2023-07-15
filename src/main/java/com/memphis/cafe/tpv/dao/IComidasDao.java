package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Comidas;

public interface IComidasDao extends CrudRepository<Comidas, Integer> {

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<Comidas> listaComidas();

}
