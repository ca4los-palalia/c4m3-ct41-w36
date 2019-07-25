package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.EstadoDAO;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Pais;

@Service
public class EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	public void save(Estado entity) {
		estadoDAO.save(entity);
	}

	public void delete(Estado entity) {
		estadoDAO.delete(entity);
	}

	public Estado getById(Long idEntity) {
		return estadoDAO.getById(idEntity);
	}

	public List<Estado> getAll() {
		return estadoDAO.getAll();
	}

	public Estado getByNombre(String nombre) {
		return estadoDAO.getByNombre(nombre);
	}

	public Estado getByAbreviatura(String abreviatura) {
		return estadoDAO.getByAbreviatura(abreviatura);
	}
	
	public Estado getByCapital(String capital) {
		return estadoDAO.getByCapital(capital);
	}
	
	public List<Estado> getByPais(Pais pais) {
		return estadoDAO.getByPais(pais);
	}
}
