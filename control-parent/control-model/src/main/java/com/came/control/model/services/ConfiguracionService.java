package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.ConfiguracionDAO;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionDAO configuracionDAO;

	public void save(Configuracion entity) {
		configuracionDAO.save(entity);
	}

	public void delete(Configuracion entity) {
		configuracionDAO.delete(entity);
	}

	public Configuracion getById(Long idEntity, Organizacion organizacion) {
		return configuracionDAO.getById(idEntity, organizacion);
	}

	public List<Configuracion> getAll() {
		return configuracionDAO.getAll();
	}

	public List<Configuracion> getByOrganizacion(Organizacion organizacion) {
		return configuracionDAO.getByOrganizacion(organizacion);
	}

	public Configuracion getByClaveAndOrg(String clave, Organizacion organizacion) {
		return configuracionDAO.getByClaveAndOrg(clave, organizacion);
	}
}
