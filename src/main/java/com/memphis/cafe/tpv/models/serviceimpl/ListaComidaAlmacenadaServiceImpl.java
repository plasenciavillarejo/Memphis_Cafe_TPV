package com.memphis.cafe.tpv.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memphis.cafe.tpv.models.dao.IListaComidaAlmacenadaDao;
import com.memphis.cafe.tpv.models.entity.ListaComidaAlmacenada;
import com.memphis.cafe.tpv.models.service.IListaComidaAlmacenadaService;


@Service
public class ListaComidaAlmacenadaServiceImpl implements IListaComidaAlmacenadaService{

	@Autowired
	private IListaComidaAlmacenadaDao comidaAlmacendaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ListaComidaAlmacenada> listaComidaAlmacenada() {
		return comidaAlmacendaDao.listaComidaAlmacenada();
	}

	@Override@Transactional
	public void guardarComida(ListaComidaAlmacenada comidaAlmacenada) {
		comidaAlmacendaDao.save(comidaAlmacenada);
	}

	@Override
	@Transactional
	public void borrarComida(int id) {
		comidaAlmacendaDao.deleteById(id);
	}

	@Override
	@Transactional
	public void borrarListaCompletaComida() {
		comidaAlmacendaDao.deleteAll();
		}

}
