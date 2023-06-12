package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.Vino;

public interface IVinoService {

	public List<Vino> listarVinos();
	
	public String precioVino(String nombre);
	
	public String precioCopaVino(String nombre);
	
}
