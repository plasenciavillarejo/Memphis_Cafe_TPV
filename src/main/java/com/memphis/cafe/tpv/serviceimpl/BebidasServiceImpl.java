package com.memphis.cafe.tpv.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IBebidasDao;
import com.memphis.cafe.tpv.entity.LogosIniciales;
import com.memphis.cafe.tpv.service.IBebidasService;

@Service
public class BebidasServiceImpl implements IBebidasService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BebidasServiceImpl.class);

	@Autowired
	private IBebidasDao bebidasDao;

	@Override
	public List<LogosIniciales> buscarLogosPrincipales() {
		LOGGER.info(" ## Se procede a visualizar los logos iniciales");
		
		Iterable<LogosIniciales> logosIniciales = bebidasDao.buscarLogosPrincipales();
		List<LogosIniciales> nombreLogo = new ArrayList<>();
		
		for(LogosIniciales log: logosIniciales) {
			nombreLogo.add(log);
		}
		return nombreLogo;
	}
	

	
}
