package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;

public abstract interface OficinaDAO {

	public abstract void save(Oficina entity);

	public abstract void delete(Oficina entity);

	public abstract Oficina getById(Long idEntity, Organizacion organizacion);

	public abstract List<Oficina> getAll();
	
	public abstract List<Oficina> getByOrganizacion(Organizacion organizacion);
	
	public abstract Oficina getByDireccion(Direccion direccion, Organizacion organizacion);
	
}
