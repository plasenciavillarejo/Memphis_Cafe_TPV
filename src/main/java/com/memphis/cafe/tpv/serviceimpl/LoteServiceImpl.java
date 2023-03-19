package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.ILoteDao;
import com.memphis.cafe.tpv.entity.Lotes;
import com.memphis.cafe.tpv.service.ILoteService;

@Service
public class LoteServiceImpl implements ILoteService {

	@Autowired
	private ILoteDao loteDao;

	@Override
	public List<Lotes> listaLotes() {
		return loteDao.listaLotes();
	}

}
