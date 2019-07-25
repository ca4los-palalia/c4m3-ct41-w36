package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.OrganizacionDAO;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Organizacion;

@Service
public class OrganizacionService {

	@Autowired
	private OrganizacionDAO organizacionDAO;

	public void save(Organizacion entity) {
		organizacionDAO.save(entity);
	}

	public void delete(Organizacion entity) {
		organizacionDAO.delete(entity);
	}

	public Organizacion getById(Long idEntity) {
		return organizacionDAO.getById(idEntity);
	}

	public List<Organizacion> getAll() {
		return organizacionDAO.getAll();
	}

	public Organizacion getByRfc(String rfc) {
		return organizacionDAO.getByRfc(rfc);
	}

	public Organizacion getByGeolocalizacion(Geolocalizacion geolocalizacion) {
		return organizacionDAO.getByGeolocalizacion(geolocalizacion);
	}
}
