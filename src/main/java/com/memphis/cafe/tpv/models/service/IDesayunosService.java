package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.Desayuno;

public interface IDesayunosService {

	public List<Desayuno> listaDesayunos();
	
	public String precioDesayunoMedia(String nombre);
	
	public String precioDesayunoEntera(String nombre);
}
