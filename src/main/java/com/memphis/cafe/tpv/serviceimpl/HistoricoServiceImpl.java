package com.memphis.cafe.tpv.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IHistoricoDao;
import com.memphis.cafe.tpv.entity.Historico;
import com.memphis.cafe.tpv.service.IHistoricoService;

@Service
public class HistoricoServiceImpl implements IHistoricoService{

	@Autowired
	private IHistoricoDao historicoDao;

	@Override
	public Historico guardarCuenta(Historico historico) {
		return historicoDao.save(historico);
	}
	
}
