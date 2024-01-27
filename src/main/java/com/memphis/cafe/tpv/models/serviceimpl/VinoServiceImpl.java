package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IVinoDao;
import com.memphis.cafe.tpv.models.entity.Vino;
import com.memphis.cafe.tpv.models.service.IVinoService;

@Service
public class VinoServiceImpl implements IVinoService{

	
	@Autowired
	private IVinoDao vinoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Vino> listarVinos() {
		return vinoDao.listarVinos();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioVino(String nombre) {
		return vinoDao.precioVino(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public String precioCopaVino(String nombre) {
		return vinoDao.precioCopaVino(nombre);
	}

}
