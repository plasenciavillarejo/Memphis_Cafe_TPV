package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.Cerveza;

public interface ICervezasService {

	public List<Cerveza> listaCervezas();
	
	public String precioCervezas(String nombre);
}
