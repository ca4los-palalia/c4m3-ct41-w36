package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.NacionalidadDAO;
import com.came.control.model.domain.Nacionalidad;

@Service
public class NacionalidadService {

	@Autowired
	private NacionalidadDAO nacionalidadDAO;

	public void save(Nacionalidad entity) {
		nacionalidadDAO.save(entity);
	}

	public void delete(Nacionalidad entity) {
		nacionalidadDAO.delete(entity);
	}

	public Nacionalidad getById(Long idEntity) {
		return nacionalidadDAO.getById(idEntity);
	}

	public List<Nacionalidad> getAll() {
		return nacionalidadDAO.getAll();
	}

	public Nacionalidad getByIdCodigoPais(String codigoPAis) {
		return nacionalidadDAO.getByIdCodigoPais(codigoPAis);
	}

	public Nacionalidad getByNombre(String nombreNacionalidad) {
		return nacionalidadDAO.getByNombre(nombreNacionalidad);
	}

	public Nacionalidad getByClave(String claveNacinalidad) {
		return nacionalidadDAO.getByClave(claveNacinalidad);
	}
}
