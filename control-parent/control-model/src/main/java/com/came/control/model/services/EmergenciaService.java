package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.EmergenciaDAO;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Persona;

@Service
public class EmergenciaService {

	@Autowired
	private EmergenciaDAO emergenciaDAO;

	public void save(Emergencia entity) {
		emergenciaDAO.save(entity);
	}

	public void delete(Emergencia entity) {
		emergenciaDAO.delete(entity);
	}

	public Emergencia getById(Long idEntity) {
		return emergenciaDAO.getById(idEntity);
	}

	public List<Emergencia> getAll() {
		return emergenciaDAO.getAll();
	}

	public Emergencia getByPersona(Persona persona) {
		return emergenciaDAO.getByPersona(persona);
	}

}
