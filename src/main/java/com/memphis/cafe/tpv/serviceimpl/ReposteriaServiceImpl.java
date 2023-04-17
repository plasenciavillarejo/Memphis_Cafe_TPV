package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IReposteriaDao;
import com.memphis.cafe.tpv.entity.Reposteria;
import com.memphis.cafe.tpv.service.IReposteriaService;

@Service
public class ReposteriaServiceImpl implements IReposteriaService{

	@Autowired
	private IReposteriaDao reposteriaDao;
	
	@Override
	public List<Reposteria> listaReposteria() {
		return reposteriaDao.listaReposteria();
	}

}