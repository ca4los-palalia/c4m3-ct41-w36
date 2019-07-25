package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Persona;

public abstract interface EmergenciaDAO {

	public abstract void save(Emergencia entity);

	public abstract void delete(Emergencia entity);

	public abstract Emergencia getById(Long idEntity);

	public abstract List<Emergencia> getAll();
	
	public abstract Emergencia getByPersona(Persona persona);
	
}
