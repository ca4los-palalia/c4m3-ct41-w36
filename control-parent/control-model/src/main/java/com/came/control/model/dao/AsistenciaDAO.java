package com.came.control.model.dao;

import java.util.Date;
import java.util.List;

import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

public abstract interface AsistenciaDAO {

	public abstract void save(Asistencia entity);

	public abstract void delete(Asistencia entity);

	public abstract Asistencia getById(Long idEntity, Organizacion organizacion);

	public abstract List<Asistencia> getAll();
	
	public abstract List<Asistencia> getAllWhithFecha(Date fecha);

	public abstract List<Asistencia> getByOrganizacion(Organizacion organizacion, Date fecha);
	
	public abstract List<Asistencia> getByFinalizados(boolean finalizado, Organizacion organizacion);
	
	public abstract Asistencia getByUsrAndDate(Usuario usuario, Date date, Organizacion organizacion);
	
	public abstract List<Asistencia> getByUsrAndDateWeek(Usuario usuario, Date fechaInicio, Date fechaFin, Organizacion organizacion);
		
	public abstract List<Asistencia> getByFecha(Date fecha, Organizacion organizacion);

}
