package com.memphis.cafe.tpv.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.models.entity.ListaComidaAlmacenada;

@Service
public interface IListaComidaAlmacenadaService {

	public List<ListaComidaAlmacenada> listaComidaAlmacenada();
	
	public void guardarComida(ListaComidaAlmacenada comidaAlmacenada);

	public void borrarComida(int id);

	public void borrarListaCompletaComida();
	
}
