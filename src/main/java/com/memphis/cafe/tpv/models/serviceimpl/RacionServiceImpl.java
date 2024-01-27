package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IRacionDao;
import com.memphis.cafe.tpv.models.entity.Racion;
import com.memphis.cafe.tpv.models.service.IRacionService;

@Service
public class RacionServiceImpl implements IRacionService{

	@Autowired
	private IRacionDao racionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Racion> listaRaciones() {
		return racionDao.listaRaciones();
	}
	
}
