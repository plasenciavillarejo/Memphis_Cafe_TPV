package com.memphis.cafe.tpv.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.memphis.cafe.tpv.models.entity.Historico;

public interface IHistoricoService {

	public Historico guardarCuenta(Historico historico);
	
	public Page<Historico> findAllPaginable(Pageable pageable);
	
	public List<Historico> findAllByName(String nombre);
	
	public List<Historico> findAllByDate(String dia);

}
