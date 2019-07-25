package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Pais;

public abstract interface PaisDAO {

	public abstract void save(Pais entity);

	public abstract void delete(Pais entity);

	public abstract Pais getById(Long idEntity);

	public abstract List<Pais> getAll();

	public abstract Pais getByNombre(String nombre);

	public abstract Pais getByClave(String clave);
}
