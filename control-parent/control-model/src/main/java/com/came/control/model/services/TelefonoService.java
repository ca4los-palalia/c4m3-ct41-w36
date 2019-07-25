package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.TelefonoDAO;
import com.came.control.model.domain.Telefono;

@Service
public class TelefonoService {

	@Autowired
	private TelefonoDAO telefonoDAO;

	public void save(Telefono entity) {
		telefonoDAO.save(entity);
	}

	public void delete(Telefono entity) {
		telefonoDAO.delete(entity);
	}

	public Telefono getById(Long idEntity) {
		return telefonoDAO.getById(idEntity);
	}

	public List<Telefono> getAll() {
		return telefonoDAO.getAll();
	}
}
