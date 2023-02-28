package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IDesayunosDao;
import com.memphis.cafe.tpv.entity.Desayuno;
import com.memphis.cafe.tpv.service.IDesayunosService;

@Service
public class DesayunosServiceImpl implements IDesayunosService {

	@Autowired
	private IDesayunosDao desayunoDao;

	@Override
	public List<Desayuno> listaDesayunos() {
		return desayunoDao.listaDesayunos();
	}

}
