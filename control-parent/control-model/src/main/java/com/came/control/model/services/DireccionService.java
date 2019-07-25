package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.DireccionDAO;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;

@Service
public class DireccionService {

	@Autowired
	private DireccionDAO direccionDAO;

	public void save(Direccion entity) {
		direccionDAO.save(entity);
	}

	public void delete(Direccion entity) {
		direccionDAO.delete(entity);
	}

	public Direccion getById(Long idEntity) {
		return direccionDAO.getById(idEntity);
	}

	public List<Direccion> getAll() {
		return direccionDAO.getAll();
	}

	public List<Direccion> getByCp(String cp) {
		return direccionDAO.getByCp(cp);
	}

	public List<Direccion> getByEstado(Estado estado) {
		return direccionDAO.getByEstado(estado);
	}

}
