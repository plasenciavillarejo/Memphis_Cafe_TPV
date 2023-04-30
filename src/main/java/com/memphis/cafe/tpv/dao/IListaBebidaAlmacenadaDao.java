package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;

public interface IListaBebidaAlmacenadaDao extends CrudRepository<ListaBebidaAlmacenada, Integer>{

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<ListaBebidaAlmacenada> listaBebidaAlmacenada();

	@Query(value ="DELETE FROM #{#entityName} c WHERE c.id = :id")
	public void borrarBebida(int id);
	
	@Modifying
	@Query(value ="DELETE FROM ListaBebidaAlmacenada c WHERE c.id >= 0", nativeQuery = true)
	public void borrarListaCompleta();
}
