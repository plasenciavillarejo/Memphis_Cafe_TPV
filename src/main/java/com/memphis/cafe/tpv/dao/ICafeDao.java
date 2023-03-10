package com.memphis.cafe.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.memphis.cafe.tpv.entity.Cafe;

public interface ICafeDao extends CrudRepository<Cafe, Integer> {

	@Query(value = "SELECT c FROM #{#entityName} c")
	public List<Cafe> listaCafes();
	
}
