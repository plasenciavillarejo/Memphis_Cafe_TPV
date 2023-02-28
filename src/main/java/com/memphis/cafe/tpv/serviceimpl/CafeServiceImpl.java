package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.ICafeDao;
import com.memphis.cafe.tpv.entity.Cafe;
import com.memphis.cafe.tpv.service.ICafeService;

@Service
public class CafeServiceImpl implements ICafeService{

	@Autowired
	private ICafeDao cafeDao;
	
	@Override
	public List<Cafe> listaCafes() {
		return cafeDao.listaCafes();
	}

}
