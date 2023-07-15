package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.dao.IHistoricoDao;
import com.memphis.cafe.tpv.entity.Historico;
import com.memphis.cafe.tpv.service.IHistoricoService;

@Service
public class HistoricoServiceImpl implements IHistoricoService{

	@Autowired
	private IHistoricoDao historicoDao;

	@Override
	@Transactional
	public Historico guardarCuenta(Historico historico) {
		return historicoDao.save(historico);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Historico> findAllPaginable(Pageable pageable) {
		return historicoDao.findAll(pageable);
	}

	@Override
	public List<Historico> findAllByName(String nombre) {
		return historicoDao.findAllByName(nombre);
	}

	@Override
	public List<Historico> findAllByDate(String dia) {
		return historicoDao.findAllByDate(dia);
	}


	
}
