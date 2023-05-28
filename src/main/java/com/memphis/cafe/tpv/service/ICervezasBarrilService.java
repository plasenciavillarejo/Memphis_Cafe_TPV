package com.memphis.cafe.tpv.service;

import java.util.List;

import com.memphis.cafe.tpv.entity.CervezaBarril;

public interface ICervezasBarrilService {

	public List<CervezaBarril> listaCervezasBarril();

	public String precioCervezasBarril(String nombre);
}
