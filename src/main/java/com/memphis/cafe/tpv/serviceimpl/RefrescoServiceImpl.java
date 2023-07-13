package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.IRefrescoDao;
import com.memphis.cafe.tpv.entity.Refresco;
import com.memphis.cafe.tpv.service.IRefrescoService;

@Service
public class RefrescoServiceImpl implements IRefrescoService{

	@Autowired
	private IRefrescoDao refrescoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Refresco> listaRefrescos() {
		return refrescoDao.listaRefrescos();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioRefresco(String nombre) {
		return refrescoDao.precioRefresco(nombre);
	}

	
	
}
