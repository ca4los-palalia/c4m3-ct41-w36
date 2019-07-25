package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.GrupoSanguineo;

public abstract interface GrupoSanguineoDAO {

	public abstract void save(GrupoSanguineo entity);

	public abstract void delete(GrupoSanguineo entity);

	public abstract GrupoSanguineo getById(Long idEntity);

	public abstract List<GrupoSanguineo> getAll();
	
	public abstract GrupoSanguineo getByNombre(String nombre);
}
