package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.Cafe;

public interface ICafeService {

	public List<Cafe> listaCafes();
	
	public String precioCafe(String nombre);
	
	public String precioCafeAumentaCantidad(String precio);
}
