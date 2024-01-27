package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.ICarneDao;
import com.memphis.cafe.tpv.models.entity.Carne;
import com.memphis.cafe.tpv.models.service.ICarneService;

@Service
public class CarneServiceImpl implements ICarneService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarneServiceImpl.class);

	@Autowired
	private ICarneDao carneDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Carne> listaCarnes() {
		return carneDao.listaCarnes();
	}

}
