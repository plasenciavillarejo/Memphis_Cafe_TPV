package com.memphis.cafe.tpv.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.IPescadoDao;
import com.memphis.cafe.tpv.entity.Pescado;
import com.memphis.cafe.tpv.service.IPescadoService;

@Service
public class PescadoServiceImpl implements IPescadoService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PescadoServiceImpl.class);

	
	@Autowired
	private IPescadoDao pescadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pescado> listaPescado() {
		
		LOGGER.info("Se procece a listar la carta de pescado");
		List<Pescado> listaPescado = new ArrayList<>();
		
		try {
			listaPescado = pescadoDao.listaPescado();
			LOGGER.info("La lista de Pescado contiene elementos");
		} catch (Exception e) {
			LOGGER.error("Se producido un error a la hora de listar los pescados.", e.getMessage());
			listaPescado = null;
		}
		return listaPescado;
	}

}
