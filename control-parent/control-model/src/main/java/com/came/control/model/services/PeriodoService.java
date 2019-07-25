package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.PeriodoDAO;
import com.came.control.model.domain.Periodo;

@Service
public class PeriodoService {

	@Autowired
	private PeriodoDAO periodoDAO;

	public void save(Periodo entity) {
		periodoDAO.save(entity);
	}

	public void delete(Periodo entity) {
		periodoDAO.delete(entity);
	}

	public Periodo getById(Long idEntity) {
		return periodoDAO.getById(idEntity);
	}

	public List<Periodo> getAll() {
		return periodoDAO.getAll();
	}

	public Periodo getByNombre(String nombre) {
		return periodoDAO.getByNombre(nombre);
	}
}
