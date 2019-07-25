package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.UsuarioDAO;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	public void save(Usuario entity) {
		usuarioDAO.save(entity);
	}

	public void delete(Usuario entity) {
		usuarioDAO.delete(entity);
	}

	public Usuario getById(Long idEntity, Organizacion organizacion) {
		return usuarioDAO.getById(idEntity, organizacion);
	}

	public List<Usuario> getAll() {
		return usuarioDAO.getAll();
	}

	public List<Usuario> getByOrganizacion(Organizacion organizacion) {
		return usuarioDAO.getByOrganizacion(organizacion);
	}

	public List<Usuario> getByEstatus(Estatus estatus, Organizacion organizacion) {
		return usuarioDAO.getByEstatus(estatus, organizacion);
	}

	public List<Usuario> getByOficina(Oficina oficina, Organizacion organizacion) {
		return usuarioDAO.getByOficina(oficina, organizacion);
	}
	
	public List<Usuario> getByRol(Rol rol, Organizacion organizacion) {
		return usuarioDAO.getByRol(rol, organizacion);
	}

	public Usuario getAutenticacWithNip(String nip, Organizacion organizacion) {

		return usuarioDAO.getAutenticacWithNip(nip, organizacion);
	}

	public Usuario getAutenticacWithWeb(String usuario, String contrasenia, Organizacion organizacion) {
		return usuarioDAO.getAutenticacWithWeb(usuario, contrasenia, organizacion);
	}
}
