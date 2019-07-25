package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Municipio;

public abstract interface MunicipioDAO {

	public abstract void save(Municipio entity);

	public abstract void delete(Municipio entity);

	public abstract Municipio getById(Long idEntity);

	public abstract List<Municipio> getAll();
	
	public abstract Municipio getByNombre(String nombre);
	
	public abstract List<Municipio> getByEstado(Estado estado);
	
}
