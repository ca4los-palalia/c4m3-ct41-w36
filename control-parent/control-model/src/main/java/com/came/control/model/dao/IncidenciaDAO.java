package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;

public abstract interface IncidenciaDAO {

	public abstract void save(Incidencia entity);

	public abstract void delete(Incidencia entity);

	public abstract Incidencia getById(Long idEntity, Organizacion organizacion);

	public abstract List<Incidencia> getAll();
	
	public abstract List<Incidencia> getByOrganizacion(Organizacion organizacion);
	
	public abstract Incidencia getByNombre(String nombre, Organizacion organizacion);
	
	public abstract Incidencia getByClave(String clave, Organizacion organizacion);

	
}
