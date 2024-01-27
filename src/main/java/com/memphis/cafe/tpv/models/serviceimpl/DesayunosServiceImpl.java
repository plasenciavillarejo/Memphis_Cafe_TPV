package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IDesayunosDao;
import com.memphis.cafe.tpv.models.entity.Desayuno;
import com.memphis.cafe.tpv.models.service.IDesayunosService;

@Service
public class DesayunosServiceImpl implements IDesayunosService {

	@Autowired
	private IDesayunosDao desayunoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Desayuno> listaDesayunos() {
		return desayunoDao.listaDesayunos();
	}

	@Override
	@Transactional(readOnly = true)
	public String precioDesayunoMedia(String nombre) {
		return desayunoDao.precioDesayunoMedia(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public String precioDesayunoEntera(String nombre) {
		return desayunoDao.precioDesayunoEntera(nombre);
	}

}
