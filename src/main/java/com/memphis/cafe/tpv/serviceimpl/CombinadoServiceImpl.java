package com.memphis.cafe.tpv.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.ICombinadoDao;
import com.memphis.cafe.tpv.entity.Combinado;
import com.memphis.cafe.tpv.service.ICombinadoService;

@Service
public class CombinadoServiceImpl implements ICombinadoService {

	@Autowired
	private ICombinadoDao combinadoDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(PescadoServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Combinado> listaCombinadosConLotes() {
		LOGGER.info("Se procece a listar la carta de combinados junto a los lotes");
		List<Combinado> listaCombinadoConLotes = new ArrayList<>();
		try {
			listaCombinadoConLotes = combinadoDao.listaCombinadosConLotes();
			LOGGER.info("La lista de Pescado contiene elementos");
		} catch (Exception e) {
			LOGGER.error("Se producido un error a la hora de listar los combinados junto a los lotes.", e.getMessage());
			listaCombinadoConLotes = null;
		}
		return listaCombinadoConLotes;
	}

}
