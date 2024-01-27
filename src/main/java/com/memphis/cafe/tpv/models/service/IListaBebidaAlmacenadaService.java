package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.ListaBebidaAlmacenada;

public interface IListaBebidaAlmacenadaService {

	public List<ListaBebidaAlmacenada> listaBebidaAlmacenada();
	
	public void guardarBebida(ListaBebidaAlmacenada bebidaAlmacenada);

	public void borrarBebida(int id);

	public void borrarListaCompleta();
	
}
