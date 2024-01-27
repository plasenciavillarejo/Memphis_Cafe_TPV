package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.ICervezasBarrilDao;
import com.memphis.cafe.tpv.models.entity.CervezaBarril;
import com.memphis.cafe.tpv.models.service.ICervezasBarrilService;

@Service
public class CervezasBarrilServiceImpl implements ICervezasBarrilService {

	@Autowired
	private ICervezasBarrilDao cervezasBarrilDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CervezaBarril> listaCervezasBarril() {
		return cervezasBarrilDao.listaCervezasBarril();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioCervezasBarril(String nombre) {
		return cervezasBarrilDao.precioCervezasBarril(nombre);
	}

}
