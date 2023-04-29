package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Cafe;

public interface ICafeDao extends CrudRepository<Cafe, Integer> {

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<Cafe> listaCafes();

	@Query(value = "SELECT c.precio FROM #{#entityName} c WHERE c.nombre = ?1 ")
	public String precioCafe(String nombre);
	
	
	@Query(value = "select precio from cafes_carajillos_infusiones where precio=precio limit 1 ", nativeQuery = true)
	public String precioCafeAumentaCantidad(String precio);
	
}
