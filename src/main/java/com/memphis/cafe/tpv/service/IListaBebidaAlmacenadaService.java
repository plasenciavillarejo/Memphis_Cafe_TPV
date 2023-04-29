package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;

public interface IListaBebidaAlmacenadaService {

	public List<ListaBebidaAlmacenada> listaBebidaAlmacenada();
	
	public void guardarBebida(ListaBebidaAlmacenada bebidaAlmacenada);

	public void borrarBebida(int id);
	
}
