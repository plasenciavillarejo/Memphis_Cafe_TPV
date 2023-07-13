package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.ICafeDao;
import com.memphis.cafe.tpv.entity.Cafe;
import com.memphis.cafe.tpv.service.ICafeService;

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
