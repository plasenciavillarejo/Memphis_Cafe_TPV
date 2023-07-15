package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.memphis.cafe.tpv.entity.Historico;

public interface IHistoricoDao extends JpaRepository<Historico, Integer>{

	@Query(value = "select c from #{#entityName} c where c.mesero like :nombre%")
	public List<Historico> findAllByName(@Param("nombre") String nombre);
	
	@Query(value = "select c from #{#entityName} c where c.dia = :dia")
	public List<Historico> findAllByDate(@Param("dia") String dia);

}
