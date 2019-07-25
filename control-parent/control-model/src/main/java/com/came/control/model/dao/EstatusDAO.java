package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Estatus;

public abstract interface EstatusDAO {

	public abstract void save(Estatus entity);

	public abstract void delete(Estatus entity);

	public abstract Estatus getById(Long idEntity);

	public abstract List<Estatus> getAll();
	
	public abstract Estatus getByNombre(String nombre);
}
