package com.memphis.cafe.tpv.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.memphis.cafe.tpv.models.entity.Comanda;

public interface IComandaDao extends JpaRepository<Comanda, Long>{
	
	@Query(value = "select c from Comanda c"
		     + " left join fetch c.listaComidaAlmacenada listaComida"
		     + " left join fetch c.listaBebidaAlmacenada listaBebida"
		     + " where c.idComanda = :idComanda")
		public Comanda recuperarComanda(@Param("idComanda") Long idComanda);
	
}
