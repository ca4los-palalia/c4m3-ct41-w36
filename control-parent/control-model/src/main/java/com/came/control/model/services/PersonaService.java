package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.PersonaDAO;
import com.came.control.model.domain.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaDAO personaDAO;

	public void save(Persona entity) {
		personaDAO.save(entity);
	}

	public void delete(Persona entity) {
		personaDAO.delete(entity);
	}

	public Persona getById(Long idEntity) {
		return personaDAO.getById(idEntity);
	}

	public List<Persona> getAll() {
		return personaDAO.getAll();
	}
}
