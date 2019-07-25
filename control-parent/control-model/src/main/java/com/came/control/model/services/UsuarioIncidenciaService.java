package com.came.control.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.UsuarioIncidenciaDAO;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;

@Service
public class UsuarioIncidenciaService {

	@Autowired
	private UsuarioIncidenciaDAO usuarioIncidenciaDAO;

	public void save(UsuarioIncidencia entity) {
		usuarioIncidenciaDAO.save(entity);
	}

	public void delete(UsuarioIncidencia entity) {
		usuarioIncidenciaDAO.delete(entity);
	}

	public UsuarioIncidencia getById(Long idEntity, Organizacion organizacion) {
		return usuarioIncidenciaDAO.getById(idEntity, organizacion);
	}

	public List<UsuarioIncidencia> getAll() {
		return usuarioIncidenciaDAO.getAll();
	}

	public List<UsuarioIncidencia> getByOrganizacion(Organizacion organizacion) {
		return usuarioIncidenciaDAO.getByOrganizacion(organizacion);
	}

	public List<UsuarioIncidencia> getByUsuario(Usuario usuario, Organizacion organizacion) {
		return usuarioIncidenciaDAO.getByUsuario(usuario, organizacion);
	}

	public List<UsuarioIncidencia> getByUsuarioAndFecha(Usuario usuario, Organizacion organizacion, Date fecha) {
		return usuarioIncidenciaDAO.getByUsuarioAndFecha(usuario, organizacion, fecha);
	}

	public List<UsuarioIncidencia> getByIncidenciaAndFecha(Incidencia incidencia, Organizacion organizacion,
			Date fecha) {
		return usuarioIncidenciaDAO.getByIncidenciaAndFecha(incidencia, organizacion, fecha);
	}

}
