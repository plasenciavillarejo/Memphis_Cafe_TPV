package com.memphis.cafe.tpv.models.service;

import org.springframework.data.repository.query.Param;

import com.memphis.cafe.tpv.models.entity.Comanda;

public interface IComandaService {

	public void guardarComanda(Comanda comanda);
	
	public Comanda recuperarComanda(@Param("idComanda") Long idComanda);
	
	public void eliminarComanda(@Param("idComanda") Long idComanda);
}
