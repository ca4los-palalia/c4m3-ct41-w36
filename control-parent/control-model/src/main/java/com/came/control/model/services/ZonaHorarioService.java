package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.ZonaHorarioDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.ZonaHorario;

@Service
public class ZonaHorarioService {

	@Autowired
	private ZonaHorarioDAO zonaHorarioDAO;

	public void save(ZonaHorario entity) {
		zonaHorarioDAO.save(entity);
	}

	public void delete(ZonaHorario entity) {
		zonaHorarioDAO.delete(entity);
	}

	public ZonaHorario getById(Long idEntity) {
		return zonaHorarioDAO.getById(idEntity);
	}
	
	public ZonaHorario getByZonaHorario(String zonaHoraria, Organizacion organizacion) {
		return zonaHorarioDAO.getByZonaHorario(zonaHoraria, organizacion);
	}

	public List<ZonaHorario> getAll() {
		return zonaHorarioDAO.getAll();
	}

	public List<ZonaHorario> getByOrganizacion(Organizacion org){
		return zonaHorarioDAO.getByOrganizacion(org);
	}
}
