package com.came.control.model.dao;

import java.util.Date;
import java.util.List;

import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;

public abstract interface UsuarioIncidenciaDAO {

	public abstract void save(UsuarioIncidencia entity);

	public abstract void delete(UsuarioIncidencia entity);

	public abstract UsuarioIncidencia getById(Long idEntity, Organizacion organizacion);

	public abstract List<UsuarioIncidencia> getAll();
	
	public abstract List<UsuarioIncidencia> getByOrganizacion(Organizacion organizacion);
	
	public abstract List<UsuarioIncidencia> getByUsuario(Usuario usuario, Organizacion organizacion);
	
	public abstract List<UsuarioIncidencia> getByUsuarioAndFecha(Usuario usuario, Organizacion organizacion, Date fecha);
	
	public abstract List<UsuarioIncidencia> getByIncidenciaAndFecha(Incidencia incidencia, Organizacion organizacion, Date fecha);
	
}
