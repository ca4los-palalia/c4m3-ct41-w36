package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;

public abstract interface DatosGeneralesDAO {

	public abstract void save(DatosGenerales entity);

	public abstract void delete(DatosGenerales entity);

	public abstract DatosGenerales getById(Long idEntity, Organizacion organizacion);

	public abstract List<DatosGenerales> getAll();

	public abstract List<DatosGenerales> getByOrganizacion(Organizacion organizacion);

	public abstract DatosGenerales getByPersona(Persona persona, Organizacion organizacion);

	public abstract DatosGenerales getByRfc(String rfc, Organizacion organizacion);

	public abstract DatosGenerales getByNss(String nss, Organizacion organizacion);

	public abstract DatosGenerales getByCurp(String curp, Organizacion organizacion);

	public abstract DatosGenerales getByEstadoCivil(EstadoCivil estadoCivil, Organizacion organizacion);

}
