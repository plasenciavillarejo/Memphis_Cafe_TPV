package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.ICervezasDao;
import com.memphis.cafe.tpv.entity.Cerveza;
import com.memphis.cafe.tpv.service.ICervezasService;

@Service
public class CervezasServiceImpl implements ICervezasService{

	@Autowired
	private ICervezasDao cervezasDao;
	
	@Override
	public List<Cerveza> listaCervezas() {
		return cervezasDao.listaCervezas();
	}

	@Override
	public String precioCervezas(String nombre) {
		return cervezasDao.precioCervezas(nombre);
	}

}
