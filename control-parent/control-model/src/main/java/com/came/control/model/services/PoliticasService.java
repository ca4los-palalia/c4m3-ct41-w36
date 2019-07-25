package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.PoliticasDAO;
import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Politicas;

@Service
public class PoliticasService {

	@Autowired
	private PoliticasDAO politicasDAO;

	public void save(Politicas entity) {
		politicasDAO.save(entity);
	}

	public void delete(Politicas entity) {
		politicasDAO.delete(entity);
	}

	public Politicas getById(Long idEntity) {
		return politicasDAO.getById(idEntity);
	}

	public List<Politicas> getAll() {
		return politicasDAO.getAll();
	}

	public List<Politicas> getByPeriodo(Periodo periodo) {
		return politicasDAO.getByPeriodo(periodo);
	}
}
