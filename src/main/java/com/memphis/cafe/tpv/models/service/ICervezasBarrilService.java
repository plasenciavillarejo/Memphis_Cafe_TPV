package com.memphis.cafe.tpv.models.service;

import java.util.List;

import com.memphis.cafe.tpv.models.entity.CervezaBarril;

public interface ICervezasBarrilService {

	public List<CervezaBarril> listaCervezasBarril();

	public String precioCervezasBarril(String nombre);
}
