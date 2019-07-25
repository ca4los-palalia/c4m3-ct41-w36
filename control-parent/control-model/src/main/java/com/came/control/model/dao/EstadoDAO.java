package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Pais;

public abstract interface EstadoDAO {

	public abstract void save(Estado entity);

	public abstract void delete(Estado entity);

	public abstract Estado getById(Long idEntity);

	public abstract List<Estado> getAll();
	
	public abstract Estado getByNombre(String nombre);
	
	public abstract Estado getByAbreviatura(String abreviatura);
	
	public abstract Estado getByCapital(String capital);
	
	public abstract List<Estado> getByPais(Pais pais);
}
