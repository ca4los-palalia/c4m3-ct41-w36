package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;

public abstract interface RolDAO {

	public abstract void save(Rol entity);

	public abstract void delete(Rol entity);

	public abstract Rol getById(Long idEntity);

	public abstract List<Rol> getAll();
	
	public abstract Rol getByNombre(String nombre);
	
	public abstract List<Rol> getByOrganizacion(Organizacion organizacion);
}
