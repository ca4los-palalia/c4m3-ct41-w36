package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Periodo;

public abstract interface PeriodoDAO {

	public abstract void save(Periodo entity);

	public abstract void delete(Periodo entity);

	public abstract Periodo getById(Long idEntity);

	public abstract List<Periodo> getAll();
	
	public abstract Periodo getByNombre(String nombre);
}
