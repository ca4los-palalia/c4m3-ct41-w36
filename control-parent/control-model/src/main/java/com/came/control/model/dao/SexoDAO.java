package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Sexo;

public abstract interface SexoDAO {

	public abstract void save(Sexo entity);

	public abstract void delete(Sexo entity);

	public abstract Sexo getById(Long idEntity);

	public abstract List<Sexo> getAll();
	
	public abstract Sexo getByNombre(String nombre);
	
}
