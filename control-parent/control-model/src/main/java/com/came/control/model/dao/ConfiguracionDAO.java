package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;

public abstract interface ConfiguracionDAO {

	public abstract void save(Configuracion entity);

	public abstract void delete(Configuracion entity);

	public abstract Configuracion getById(Long idEntity, Organizacion organizacion);

	public abstract List<Configuracion> getAll();
	
	public abstract List<Configuracion> getByOrganizacion(Organizacion organizacion);
	
	public abstract Configuracion getByClaveAndOrg(String clave, Organizacion organizacion);
	
}
