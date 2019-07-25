package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.PoliticaHorario;

public abstract interface PoliticaHorarioDAO {

	public abstract void save(PoliticaHorario entity);

	public abstract void delete(PoliticaHorario entity);

	public abstract PoliticaHorario getById(Long idEntity, Organizacion organizacion);

	public abstract List<PoliticaHorario> getAll();
	
	public abstract PoliticaHorario getByNombre(String nombre, Organizacion organizacion);
	
	public abstract List<PoliticaHorario> getByOrganizacion(Organizacion organizacion);
}
