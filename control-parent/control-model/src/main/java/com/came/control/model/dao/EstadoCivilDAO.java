package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.EstadoCivil;

public abstract interface EstadoCivilDAO {

	public abstract void save(EstadoCivil entity);

	public abstract void delete(EstadoCivil entity);

	public abstract EstadoCivil getById(Long idEntity);

	public abstract List<EstadoCivil> getAll();
	
	public abstract EstadoCivil getByNombre(String nombre);
}
