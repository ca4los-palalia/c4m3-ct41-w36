package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.OficinaDAO;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;

@Service
public class OficinaService {

	@Autowired
	private OficinaDAO oficinaDAO;

	public void save(Oficina entity) {
		oficinaDAO.save(entity);
	}

	public void delete(Oficina entity) {
		oficinaDAO.delete(entity);
	}

	public Oficina getById(Long idEntity, Organizacion organizacion) {
		return oficinaDAO.getById(idEntity, organizacion);
	}

	public List<Oficina> getAll() {
		return oficinaDAO.getAll();
	}

	public List<Oficina> getByOrganizacion(Organizacion organizacion) {
		return oficinaDAO.getByOrganizacion(organizacion);
	}

	public Oficina getByDireccion(Direccion direccion, Organizacion organizacion) {
		return oficinaDAO.getByDireccion(direccion, organizacion);
	}

}
