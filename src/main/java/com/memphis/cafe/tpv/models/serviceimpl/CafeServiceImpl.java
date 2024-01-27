package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.ICafeDao;
import com.memphis.cafe.tpv.models.entity.Cafe;
import com.memphis.cafe.tpv.models.service.ICafeService;

@Service
public class CafeServiceImpl implements ICafeService{

	@Autowired
	private ICafeDao cafeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cafe> listaCafes() {
		return cafeDao.listaCafes();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioCafe(String nombre) {
		return cafeDao.precioCafe(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public String precioCafeAumentaCantidad(String precio) {
		return cafeDao.precioCafeAumentaCantidad(precio);
	}

}
