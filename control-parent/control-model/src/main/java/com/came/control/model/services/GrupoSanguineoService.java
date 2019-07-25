package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.GrupoSanguineoDAO;
import com.came.control.model.domain.GrupoSanguineo;

@Service
public class GrupoSanguineoService {

	@Autowired
	private GrupoSanguineoDAO grupoSanguineoDAO;

	public void save(GrupoSanguineo entity) {
		grupoSanguineoDAO.save(entity);
	}

	public void delete(GrupoSanguineo entity) {
		grupoSanguineoDAO.delete(entity);
	}

	public GrupoSanguineo getById(Long idEntity) {
		return grupoSanguineoDAO.getById(idEntity);
	}

	public List<GrupoSanguineo> getAll() {
		return grupoSanguineoDAO.getAll();
	}

	public GrupoSanguineo getByNombre(String nombre) {
		return grupoSanguineoDAO.getByNombre(nombre);
	}
}
