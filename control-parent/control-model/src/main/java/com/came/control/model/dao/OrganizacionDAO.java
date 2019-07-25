package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Organizacion;

public abstract interface OrganizacionDAO {

	public abstract void save(Organizacion entity);

	public abstract void delete(Organizacion entity);

	public abstract Organizacion getById(Long idEntity);

	public abstract List<Organizacion> getAll();
	
	public abstract Organizacion getByRfc(String rfc);
	
	public abstract Organizacion getByGeolocalizacion(Geolocalizacion geolocalizacion);
}
