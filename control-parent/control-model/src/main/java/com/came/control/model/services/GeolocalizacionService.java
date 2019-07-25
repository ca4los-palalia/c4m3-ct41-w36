package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.GeolocalizacionDAO;
import com.came.control.model.domain.Geolocalizacion;

@Service
public class GeolocalizacionService {

	@Autowired
	private GeolocalizacionDAO geolocalizacionDAO;

	public void save(Geolocalizacion entity) {
		geolocalizacionDAO.save(entity);
	}

	public void delete(Geolocalizacion entity) {
		geolocalizacionDAO.delete(entity);
	}

	public Geolocalizacion getById(Long idEntity) {
		return geolocalizacionDAO.getById(idEntity);
	}

	public List<Geolocalizacion> getAll() {
		return geolocalizacionDAO.getAll();
	}

}
