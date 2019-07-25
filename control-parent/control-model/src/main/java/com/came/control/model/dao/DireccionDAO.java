package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;

public abstract interface DireccionDAO {

	public abstract void save(Direccion entity);

	public abstract void delete(Direccion entity);

	public abstract Direccion getById(Long idEntity);

	public abstract List<Direccion> getAll();
	
	public abstract List<Direccion> getByCp(String cp);
	
	public abstract List<Direccion> getByEstado(Estado estado);

}
