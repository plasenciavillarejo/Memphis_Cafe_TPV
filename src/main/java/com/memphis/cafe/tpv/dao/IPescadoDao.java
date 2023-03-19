package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Pescado;

public interface IPescadoDao extends CrudRepository<Pescado, Integer>{

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<Pescado> listaPescado();
	
}
