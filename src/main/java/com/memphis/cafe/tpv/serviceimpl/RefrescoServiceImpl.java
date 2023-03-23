package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IRefrescoDao;
import com.memphis.cafe.tpv.entity.Refresco;
import com.memphis.cafe.tpv.service.IRefrescoService;

@Service
public class RefrescoServiceImpl implements IRefrescoService{

	@Autowired
	private IRefrescoDao refrescoDao;
	
	@Override
	public List<Refresco> listaRefrescos() {
		return refrescoDao.listaRefrescos();
	}

	
	
}
