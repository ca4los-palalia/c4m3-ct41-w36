package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.PaisDAO;
import com.came.control.model.domain.Pais;

@Service
public class PaisService {

	@Autowired
	private PaisDAO paisDAO;

	public void save(Pais entity) {
		paisDAO.save(entity);
	}

	public void delete(Pais entity) {
		paisDAO.delete(entity);
	}

	public Pais getById(Long idEntity) {
		return paisDAO.getById(idEntity);
	}

	public List<Pais> getAll() {
		return paisDAO.getAll();
	}

	public Pais getByNombre(String nombre) {
		return paisDAO.getByNombre(nombre);
	}
	
	public Pais getByClave(String clave) {
		return paisDAO.getByClave(clave);
	}
}
