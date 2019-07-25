package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.MunicipioDAO;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Municipio;

@Service
public class MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;

	public void save(Municipio entity) {
		municipioDAO.save(entity);
	}

	public void delete(Municipio entity) {
		municipioDAO.delete(entity);
	}

	public Municipio getById(Long idEntity) {
		return municipioDAO.getById(idEntity);
	}

	public List<Municipio> getAll() {
		return municipioDAO.getAll();
	}

	public Municipio getByNombre(String nombre) {
		return municipioDAO.getByNombre(nombre);
	}

	public List<Municipio> getByEstado(Estado estado) {
		return municipioDAO.getByEstado(estado);
	}
}
