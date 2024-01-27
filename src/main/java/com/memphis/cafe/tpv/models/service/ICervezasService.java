package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.Cerveza;
import com.memphis.cafe.tpv.models.entity.CervezaBarril;

public interface ICervezasService {

	public List<Cerveza> listaCervezas();
	
	public String precioCervezas(String nombre);
	
}
