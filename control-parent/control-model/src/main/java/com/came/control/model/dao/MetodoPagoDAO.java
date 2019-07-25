package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Organizacion;

public abstract interface MetodoPagoDAO {

	public abstract void save(MetodoPago entity);

	public abstract void delete(MetodoPago entity);

	public abstract MetodoPago getById(Long idEntity);

	public abstract MetodoPago getByNombre(String nombre, Organizacion org);
	
	public abstract List<MetodoPago> getAll();
	
	public abstract List<MetodoPago> getAllByOrganizacion(Organizacion org);
	
}
