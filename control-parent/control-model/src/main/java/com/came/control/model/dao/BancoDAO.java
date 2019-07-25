package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Organizacion;

public abstract interface BancoDAO {

	public abstract void save(Banco entity);

	public abstract void delete(Banco entity);

	public abstract Banco getById(Long idEntity);

	public abstract Banco getByNombre(String nombre, Organizacion org);
	
	public abstract Banco getByClave(String clave, Organizacion org);
	
	public abstract Banco getByRfc(String rfc, Organizacion org);
	
	public abstract List<Banco> getAll();
	
	public abstract List<Banco> getAllByOrganizacion(Organizacion org);
	
}
