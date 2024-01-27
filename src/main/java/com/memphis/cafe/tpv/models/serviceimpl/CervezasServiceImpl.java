package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.ICervezasDao;
import com.memphis.cafe.tpv.models.entity.Cerveza;
import com.memphis.cafe.tpv.models.entity.CervezaBarril;
import com.memphis.cafe.tpv.models.service.ICervezasService;

@Service
public class CervezasServiceImpl implements ICervezasService{

	@Autowired
	private ICervezasDao cervezasDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cerveza> listaCervezas() {
		return cervezasDao.listaCervezas();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioCervezas(String nombre) {
		return cervezasDao.precioCervezas(nombre);
	}

}
