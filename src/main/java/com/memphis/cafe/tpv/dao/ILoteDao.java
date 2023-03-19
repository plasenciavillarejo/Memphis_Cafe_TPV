package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Lotes;

public interface ILoteDao extends CrudRepository<Lotes, Integer> {

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<Lotes> listaLotes();

}
