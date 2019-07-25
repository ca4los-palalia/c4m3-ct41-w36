package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.SexoDAO;
import com.came.control.model.domain.Sexo;

@Service
public class SexoService {

	@Autowired
	private SexoDAO sexoDAO;

	public void save(Sexo entity) {
		sexoDAO.save(entity);
	}

	public void delete(Sexo entity) {
		sexoDAO.delete(entity);
	}

	public Sexo getById(Long idEntity) {
		return sexoDAO.getById(idEntity);
	}

	public List<Sexo> getAll() {
		return sexoDAO.getAll();
	}

	public Sexo getByNombre(String nombre) {
		return sexoDAO.getByNombre(nombre);
	}

}
