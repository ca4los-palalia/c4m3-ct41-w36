package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.PoliticaHorarioDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.PoliticaHorario;

@Service
public class PoliticaHorarioService {

	@Autowired
	private PoliticaHorarioDAO politicaHorarioDAO;

	public void save(PoliticaHorario entity) {
		politicaHorarioDAO.save(entity);
	}

	public void delete(PoliticaHorario entity) {
		politicaHorarioDAO.delete(entity);
	}

	public PoliticaHorario getById(Long idEntity, Organizacion organizacion) {
		return politicaHorarioDAO.getById(idEntity, organizacion);
	}

	public List<PoliticaHorario> getAll() {
		return politicaHorarioDAO.getAll();
	}

	public List<PoliticaHorario> getByOrganizacion(Organizacion organizacion) {
		return politicaHorarioDAO.getByOrganizacion(organizacion);
	}

	public PoliticaHorario getByNombre(String nombre, Organizacion organizacion) {
		return politicaHorarioDAO.getByNombre(nombre, organizacion);
	}

}
