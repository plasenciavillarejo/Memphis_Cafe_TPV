package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IRacionDao;
import com.memphis.cafe.tpv.entity.Racion;
import com.memphis.cafe.tpv.service.IRacionService;

@Service
public class RacionServiceImpl implements IRacionService{

	@Autowired
	private IRacionDao racionDao;

	@Override
	public List<Racion> listaRaciones() {
		return racionDao.listaRaciones();
	}
	
}
