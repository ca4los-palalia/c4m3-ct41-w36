package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Politicas;

public abstract interface PoliticasDAO {

	public abstract void save(Politicas entity);

	public abstract void delete(Politicas entity);

	public abstract Politicas getById(Long idEntity);

	public abstract List<Politicas> getAll();
	
	public abstract List<Politicas> getByPeriodo(Periodo periodo);
}
