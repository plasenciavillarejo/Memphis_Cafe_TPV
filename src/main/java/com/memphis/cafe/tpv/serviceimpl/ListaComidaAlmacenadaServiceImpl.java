package com.memphis.cafe.tpv.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memphis.cafe.tpv.dao.IListaComidaAlmacenadaDao;
import com.memphis.cafe.tpv.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.service.IListaComidaAlmacenadaService;

import jakarta.transaction.Transactional;

@Service
public class ListaComidaAlmacenadaServiceImpl implements IListaComidaAlmacenadaService{

	@Autowired
	private IListaComidaAlmacenadaDao comidaAlmacendaDao;
	
	@Override
	public List<ListaComidaAlmacenada> listaComidaAlmacenada() {
		return comidaAlmacendaDao.listaComidaAlmacenada();
	}

	@Transactional
	@Override
	public void guardarComida(ListaComidaAlmacenada comidaAlmacenada) {
		comidaAlmacendaDao.save(comidaAlmacenada);
	}

	@Transactional
	@Override
	public void borrarComida(int id) {
		comidaAlmacendaDao.deleteById(id);
	}

	@Transactional
	@Override
	public void borrarListaCompletaComida() {
		comidaAlmacendaDao.deleteAll();
		}

}
