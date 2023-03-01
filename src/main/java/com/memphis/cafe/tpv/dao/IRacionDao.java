package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Racion;

public interface IRacionDao extends CrudRepository<Racion, Integer> {

	@Query(value = "select c from #{#entityName} c")
	public List<Racion> listaRaciones();

}
