package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Geolocalizacion;

public abstract interface GeolocalizacionDAO {
	
	public abstract void save(Geolocalizacion entity);

	public abstract void delete(Geolocalizacion entity);

	public abstract Geolocalizacion getById(Long idEntity);

	public abstract List<Geolocalizacion> getAll();

}
