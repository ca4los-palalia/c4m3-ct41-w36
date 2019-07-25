package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.DatosGeneralesDAO;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;

@Service
public class DatosGeneralesService {

	@Autowired
	private DatosGeneralesDAO datosGeneralesDAO;

	public void save(DatosGenerales entity) {
		datosGeneralesDAO.save(entity);
	}

	public void delete(DatosGenerales entity) {
		datosGeneralesDAO.delete(entity);
	}

	public DatosGenerales getById(Long idEntity, Organizacion organizacion) {
		return datosGeneralesDAO.getById(idEntity, organizacion);
	}

	public List<DatosGenerales> getAll() {
		return datosGeneralesDAO.getAll();
	}

	public List<DatosGenerales> getByOrganizacion(Organizacion organizacion) {
		return datosGeneralesDAO.getByOrganizacion(organizacion);
	}

	public DatosGenerales getByPersona(Persona persona, Organizacion organizacion) {
		return datosGeneralesDAO.getByPersona(persona, organizacion);
	}

	public DatosGenerales getByRfc(String rfc, Organizacion organizacion) {
		return datosGeneralesDAO.getByRfc(rfc, organizacion);
	}

	public DatosGenerales getByNss(String nss, Organizacion organizacion) {
		return datosGeneralesDAO.getByNss(nss, organizacion);
	}

	public DatosGenerales getByCurp(String curp, Organizacion organizacion) {
		return datosGeneralesDAO.getByCurp(curp, organizacion);
	}

	public DatosGenerales getByEstadoCivil(EstadoCivil estadoCivil, Organizacion organizacion) {
		return datosGeneralesDAO.getByEstadoCivil(estadoCivil, organizacion);
	}

}
