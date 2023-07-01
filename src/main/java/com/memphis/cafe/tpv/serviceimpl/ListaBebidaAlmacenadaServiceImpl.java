package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IListaBebidaAlmacenadaDao;
import com.memphis.cafe.tpv.entity.ListaBebidaAlmacenada;
import com.memphis.cafe.tpv.service.IListaBebidaAlmacenadaService;

import jakarta.transaction.Transactional;

@Service
public class ListaBebidaAlmacenadaServiceImpl implements IListaBebidaAlmacenadaService{

	@Autowired
	private IListaBebidaAlmacenadaDao bebidaAlmacendaDao;
	
	@Override
	public List<ListaBebidaAlmacenada> listaBebidaAlmacenada() {
		return bebidaAlmacendaDao.listaBebidaAlmacenada();
	}

	@Transactional
	@Override
	public void guardarBebida(ListaBebidaAlmacenada bebidaAlmacenada) {
		bebidaAlmacendaDao.save(bebidaAlmacenada);
	}

	@Transactional
	@Override
	public void borrarBebida(int id) {
		bebidaAlmacendaDao.deleteById(id);
	}

	@Transactional
	@Override
	public void borrarListaCompleta() {
		bebidaAlmacendaDao.deleteAll();
	}

}
