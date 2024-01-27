package com.memphis.cafe.tpv.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.models.entity.ListaBebidaAlmacenada;

public interface IListaBebidaAlmacenadaDao extends CrudRepository<ListaBebidaAlmacenada, Integer>{

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<ListaBebidaAlmacenada> listaBebidaAlmacenada();
	
	/*
	@Modifying
	@Query(value ="DELETE FROM ListaBebidaAlmacenada c WHERE c.id >= 0", nativeQuery = true)
	public void borrarListaCompleta();
	*/
}
