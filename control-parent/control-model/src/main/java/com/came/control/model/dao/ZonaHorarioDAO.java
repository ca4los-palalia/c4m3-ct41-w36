package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.ZonaHorario;

public abstract interface ZonaHorarioDAO {

	public abstract void save(ZonaHorario entity);

	public abstract void delete(ZonaHorario entity);

	public abstract ZonaHorario getById(Long idEntity);
	
	public abstract ZonaHorario getByZonaHorario(String zonaHoraria, Organizacion organizacion);

	public abstract List<ZonaHorario> getAll();
	
	public abstract List<ZonaHorario> getByOrganizacion(Organizacion org);
	
}
