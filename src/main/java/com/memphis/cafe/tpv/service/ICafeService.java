package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.Cafe;

public interface ICafeService {

	public List<Cafe> listaCafes();
	
	public String precioCafe(String nombre);
}
