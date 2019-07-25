package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Organizacion;

public abstract interface EscolaridadDAO {

	public abstract void save(Escolaridad entity);

	public abstract void delete(Escolaridad entity);

	public abstract Escolaridad getById(Long idEntity);

	public abstract Escolaridad getByNombre(String nombreEscolaridad, Organizacion org);
	
	public abstract List<Escolaridad> getAll();
	
	public abstract List<Escolaridad> getAllByOrganizacion(Organizacion org);

}
