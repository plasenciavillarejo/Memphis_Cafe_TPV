package com.memphis.cafe.tpv.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.models.entity.Desayuno;

public interface IDesayunosDao extends CrudRepository<Desayuno, Integer>{

	@Query(value = "select l from #{#entityName} l")
	public List<Desayuno> listaDesayunos();
	
	@Query(value = "SELECT c.precio_media FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioDesayunoMedia(String nombre);
	
	@Query(value = "SELECT c.precio_entera FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioDesayunoEntera(String nombre);
}
