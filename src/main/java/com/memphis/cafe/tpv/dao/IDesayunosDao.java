package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Desayuno;

public interface IDesayunosDao extends CrudRepository<Desayuno, Integer>{

	@Query(value = "select l from #{#entityName} l")
	public List<Desayuno> listaDesayunos();
}
