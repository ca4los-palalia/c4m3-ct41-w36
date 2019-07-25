package com.came.control.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.AsistenciaDAO;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

@Service
public class AsistenciaService {

	@Autowired
	private AsistenciaDAO asistenciaDAO;

	public void save(Asistencia entity) {
		asistenciaDAO.save(entity);
	}

	public void delete(Asistencia entity) {
		asistenciaDAO.delete(entity);
	}

	public Asistencia getById(Long idEntity, Organizacion organizacion) {
		return asistenciaDAO.getById(idEntity, organizacion);
	}

	public List<Asistencia> getAll() {
		return asistenciaDAO.getAll();
	}
	
	public List<Asistencia> getAllWhithFecha(Date fecha){
		return asistenciaDAO.getAllWhithFecha(fecha);
	}

	public List<Asistencia> getByOrganizacion(Organizacion organizacion, Date fecha) {
		return asistenciaDAO.getByOrganizacion(organizacion, fecha);
	}

	public List<Asistencia> getByFinalizados(boolean finalizado, Organizacion organizacion) {
		return asistenciaDAO.getByFinalizados(finalizado, organizacion);
	}

	public Asistencia getByUsrAndDate(Usuario usuario, Date date, Organizacion organizacion) {
		return asistenciaDAO.getByUsrAndDate(usuario, date, organizacion);
	}
	
	public List<Asistencia> getByUsrAndDateWeek(Usuario usuario, Date fechaInicio, Date fechaFin, Organizacion organizacion) {
		return asistenciaDAO.getByUsrAndDateWeek(usuario, fechaInicio, fechaFin, organizacion);
	}

	public List<Asistencia> getByFecha(Date fecha, Organizacion organizacion) {
		return asistenciaDAO.getByFecha(fecha, organizacion);
	}
}
