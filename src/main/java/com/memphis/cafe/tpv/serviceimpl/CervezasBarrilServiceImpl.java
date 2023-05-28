package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.ICervezasBarrilDao;
import com.memphis.cafe.tpv.entity.CervezaBarril;
import com.memphis.cafe.tpv.service.ICervezasBarrilService;

@Service
public class CervezasBarrilServiceImpl implements ICervezasBarrilService {

	@Autowired
	private ICervezasBarrilDao cervezasBarrilDao;
	
	@Override
	public List<CervezaBarril> listaCervezasBarril() {
		return cervezasBarrilDao.listaCervezasBarril();
	}

	@Override
	public String precioCervezasBarril(String nombre) {
		return cervezasBarrilDao.precioCervezasBarril(nombre);
	}

}
