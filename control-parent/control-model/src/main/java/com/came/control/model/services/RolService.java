package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.RolDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;

@Service
public class RolService {

	@Autowired
	private RolDAO rolDAO;

	public void save(Rol entity) {
		rolDAO.save(entity);
	}

	public void delete(Rol entity) {
		rolDAO.delete(entity);
	}

	public Rol getById(Long idEntity) {
		return rolDAO.getById(idEntity);
	}

	public List<Rol> getAll() {
		return rolDAO.getAll();
	}

	public Rol getByNombre(String nombre) {
		return rolDAO.getByNombre(nombre);
	}
	
	public List<Rol> getByOrganizacion(Organizacion organizacion){
		return rolDAO.getByOrganizacion(organizacion);
	}
}
