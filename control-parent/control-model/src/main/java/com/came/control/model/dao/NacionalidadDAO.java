package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Nacionalidad;

public abstract interface NacionalidadDAO {

	public abstract void save(Nacionalidad entity);

	public abstract void delete(Nacionalidad entity);

	public abstract Nacionalidad getById(Long idEntity);

	public abstract List<Nacionalidad> getAll();

	public abstract Nacionalidad getByIdCodigoPais(String codigoPAis);

	public abstract Nacionalidad getByNombre(String nombreNacionalidad);

	public abstract Nacionalidad getByClave(String claveNacinalidad);

}
