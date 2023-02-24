package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.LogosIniciales;

public interface IBebidasDao extends CrudRepository<LogosIniciales, Integer>{

	@Query("Select c from #{#entityName} c")
	public List<LogosIniciales> buscarLogosPrincipales();
}
