package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.Desayuno;

public interface IDesayunosService {

	public List<Desayuno> listaDesayunos();
	
	public String precioDesayunoMedia(String nombre);
	
	public String precioDesayunoEntera(String nombre);
}
