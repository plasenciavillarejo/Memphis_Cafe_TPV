package com.memphis.cafe.tpv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;

@Service
public interface IListaComidaAlmacenadaService {

	public List<ListaComidaAlmacenada> listaComidaAlmacenada();
	
	public void guardarComida(ListaComidaAlmacenada comidaAlmacenada);

	public void borrarComida(int id);

	public void borrarListaCompletaComida();
	
}
