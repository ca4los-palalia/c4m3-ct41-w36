package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.EstatusDAO;
import com.came.control.model.domain.Estatus;

@Service
public class EstatusService {

	@Autowired
	private EstatusDAO estatusDAO;

	public void save(Estatus entity) {
		estatusDAO.save(entity);
	}

	public void delete(Estatus entity) {
		estatusDAO.delete(entity);
	}

	public Estatus getById(Long idEntity) {
		return estatusDAO.getById(idEntity);
	}

	public List<Estatus> getAll() {
		return estatusDAO.getAll();
	}

	public Estatus getByNombre(String nombre) {
		return estatusDAO.getByNombre(nombre);
	}
}
