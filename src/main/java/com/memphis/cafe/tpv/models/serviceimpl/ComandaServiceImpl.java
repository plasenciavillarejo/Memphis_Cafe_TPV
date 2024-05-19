package com.memphis.cafe.tpv.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IComandaDao;
import com.memphis.cafe.tpv.models.entity.Comanda;
import com.memphis.cafe.tpv.models.service.IComandaService;

@Service
public class ComandaServiceImpl implements IComandaService {

	@Autowired
	private IComandaDao comandaDao;

	@Override
	@Transactional
	public void guardarComanda(Comanda comanda) {
		comandaDao.save(comanda);
	}

	@Override
	@Transactional(readOnly = true)
	public Comanda recuperarComanda(Long idComanda) {
		return comandaDao.recuperarComanda(idComanda);
	}

	@Override
	@Transactional
	public void eliminarComanda(Long idComanda) {
		comandaDao.deleteById(idComanda);
	}
	
}
