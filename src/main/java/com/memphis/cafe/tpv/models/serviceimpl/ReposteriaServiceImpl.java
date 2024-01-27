package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IReposteriaDao;
import com.memphis.cafe.tpv.models.entity.Reposteria;
import com.memphis.cafe.tpv.models.service.IReposteriaService;

@Service
public class ReposteriaServiceImpl implements IReposteriaService{

	@Autowired
	private IReposteriaDao reposteriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reposteria> listaReposteria() {
		return reposteriaDao.listaReposteria();
	}

}
