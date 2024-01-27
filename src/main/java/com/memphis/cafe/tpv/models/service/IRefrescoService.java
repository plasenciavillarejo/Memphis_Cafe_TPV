package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.Refresco;

public interface IRefrescoService {

	public List<Refresco> listaRefrescos();
	
	public String precioRefresco(String nombre);
}
