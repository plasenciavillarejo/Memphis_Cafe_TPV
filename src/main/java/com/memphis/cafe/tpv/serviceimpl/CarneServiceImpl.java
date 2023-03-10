package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.ICarneDao;
import com.memphis.cafe.tpv.entity.Carne;
import com.memphis.cafe.tpv.service.ICarneService;

@Service
public class CarneServiceImpl implements ICarneService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarneServiceImpl.class);

	@Autowired
	private ICarneDao carneDao;
	
	@Override
	public List<Carne> listaCarnes() {
		return carneDao.listaCarnes();
	}

}
