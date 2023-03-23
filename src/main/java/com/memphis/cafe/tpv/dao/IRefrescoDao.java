package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Refresco;

public interface IRefrescoDao extends CrudRepository<Refresco, Integer> {

	@Query(value = "select c from #{#entityName} c")
	public List<Refresco> listaRefrescos();
}
