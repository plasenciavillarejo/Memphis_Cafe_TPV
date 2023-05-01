package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;

public interface IListaComidaAlmacenadaDao extends CrudRepository<ListaComidaAlmacenada, Integer>{

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<ListaComidaAlmacenada> listaComidaAlmacenada();

	@Query(value ="DELETE FROM #{#entityName} c WHERE c.id = :id")
	public void borrarBebida(int id);
	
	@Modifying
	@Query(value ="DELETE FROM ListaComidaAlmacenada c WHERE c.id >= 0", nativeQuery = true)
	public void borrarListaCompletaComida();
}
