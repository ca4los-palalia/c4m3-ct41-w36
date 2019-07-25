package com.came.control.model.services;import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.EstadoCivilDAO;
import com.came.control.model.domain.EstadoCivil;

@Service public class EstadoCivilService {

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	public void save(EstadoCivil entity) {
		estadoCivilDAO.save(entity);
	}

	public void delete(EstadoCivil entity) {
		estadoCivilDAO.delete(entity);
	}

	public EstadoCivil getById(Long idEntity) {
		return estadoCivilDAO.getById(idEntity);
	}

	public List<EstadoCivil> getAll() {
		return estadoCivilDAO.getAll();
	}

	public EstadoCivil getByNombre(String nombre) {
		return estadoCivilDAO.getByNombre(nombre);
	}
}
