package com.memphis.cafe.tpv.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memphis.cafe.tpv.models.entity.Comanda;

public interface IComandaDao extends JpaRepository<Comanda, Long>{
	
}
