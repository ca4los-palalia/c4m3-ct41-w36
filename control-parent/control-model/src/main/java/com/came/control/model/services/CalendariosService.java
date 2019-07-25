package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.CalendariosDAO;
import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Organizacion;

@Service
public class CalendariosService {

	@Autowired
	private CalendariosDAO calendariosDAO;

	public void save(Calendarios entity) {
		calendariosDAO.save(entity);
	}

	public void delete(Calendarios entity) {
		calendariosDAO.delete(entity);
	}

	public Calendarios getById(Long idEntity, Organizacion organizacion) {
		return calendariosDAO.getById(idEntity, organizacion);
	}

	public List<Calendarios> getAll() {
		return calendariosDAO.getAll();
	}

	public List<Calendarios> getByOrganizacion(Organizacion organizacion) {
		return calendariosDAO.getByOrganizacion(organizacion);
	}

}
