package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.IComidasDao;
import com.memphis.cafe.tpv.entity.Comidas;
import com.memphis.cafe.tpv.service.IComidasService;

@Service
public class ComidasServiceImpl implements IComidasService {

	@Autowired
	private IComidasDao comidasDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comidas> listaComidas() {
		return comidasDao.listaComidas();
	}

}
