package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.IncidenciaDAO;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;

@Service
public class IncidenciaService {

	@Autowired
	private IncidenciaDAO incidenciasDAO;

	public void save(Incidencia entity) {
		incidenciasDAO.save(entity);
	}

	public void delete(Incidencia entity) {
		incidenciasDAO.delete(entity);
	}

	public Incidencia getById(Long idEntity, Organizacion organizacion) {
		return incidenciasDAO.getById(idEntity, organizacion);
	}

	public List<Incidencia> getAll() {
		return incidenciasDAO.getAll();
	}

	public List<Incidencia> getByOrganizacion(Organizacion organizacion) {
		return incidenciasDAO.getByOrganizacion(organizacion);
	}

	public Incidencia getByNombre(String nombre, Organizacion organizacion) {
		return incidenciasDAO.getByNombre(nombre, organizacion);
	}

	public Incidencia getByClave(String clave, Organizacion organizacion) {
		return incidenciasDAO.getByClave(clave, organizacion);
	}
}
