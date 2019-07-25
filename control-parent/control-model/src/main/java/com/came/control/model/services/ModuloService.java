package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.ModuloDAO;
import com.came.control.model.domain.Modulo;

@Service
public class ModuloService {
	
	@Autowired
	private ModuloDAO moduloDAO;

	public void save(Modulo modulo) {
		moduloDAO.save(modulo);
	}

	public void delete(Modulo modulo) {
		moduloDAO.delete(modulo);
	}

	public Modulo getByNombre(String nombre) {
		return moduloDAO.getByNombre(nombre);
	}

	public Modulo getByRuta(String ruta) {
		return moduloDAO.getByRuta(ruta);
	}

	public Modulo getById(Long idModulo) {
		return moduloDAO.getById(idModulo);
	}

	public List<Modulo> getAll() {
		return moduloDAO.getAll();
	}
}
