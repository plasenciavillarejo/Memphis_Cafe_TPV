package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IVinoDao;
import com.memphis.cafe.tpv.entity.Vino;
import com.memphis.cafe.tpv.service.IVinoService;

@Service
public class VinoServiceImpl implements IVinoService{

	
	@Autowired
	private IVinoDao vinoDao;
	
	@Override
	public List<Vino> listarVinos() {
		return vinoDao.listarVinos();
	}

	@Override
	public String precioVino(String nombre) {
		return vinoDao.precioVino(nombre);
	}

	@Override
	public String precioCopaVino(String nombre) {
		return vinoDao.precioCopaVino(nombre);
	}

}
