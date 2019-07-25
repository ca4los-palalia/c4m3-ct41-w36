package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Organizacion;

public abstract interface CalendariosDAO {

	public abstract void save(Calendarios entity);

	public abstract void delete(Calendarios entity);

	public abstract Calendarios getById(Long idEntity, Organizacion organizacion);

	public abstract List<Calendarios> getAll();

	public abstract List<Calendarios> getByOrganizacion(Organizacion organizacion);

}
