package com.memphis.cafe.tpv.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.models.entity.Combinado;

public interface ICombinadoDao extends CrudRepository<Combinado, Integer> {

	@Query(value = "select c from #{#entityName} c")
	public List<Combinado> listaCombinadosConLotes();
	
}
