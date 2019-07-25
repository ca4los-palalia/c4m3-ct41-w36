package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.EscolaridadDAO;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Organizacion;

@Service
public class EscolaridadService {

	@Autowired
	private EscolaridadDAO escolaridadDAO;

	public void save(Escolaridad entity) {
		escolaridadDAO.save(entity);
	}

	public void delete(Escolaridad entity) {
		escolaridadDAO.delete(entity);
	}

	public Escolaridad getById(Long idEntity) {
		return escolaridadDAO.getById(idEntity);
	}
	
	public Escolaridad getByNombre(String nombre, Organizacion org) {
		return escolaridadDAO.getByNombre(nombre, org);
	}
	
	public List<Escolaridad> getAll() {
		return escolaridadDAO.getAll();
	}
	
	public List<Escolaridad> getAll(Organizacion org) {
		return escolaridadDAO.getAllByOrganizacion(org);
	}
}
