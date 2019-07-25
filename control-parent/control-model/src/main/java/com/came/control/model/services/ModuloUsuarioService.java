package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.ModuloUsuarioDAO;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Usuario;

@Service
public class ModuloUsuarioService {
	@Autowired
	private ModuloUsuarioDAO moduloUsuarioDAO;

	public void save(ModuloUsuario moduloUsuario) {
		moduloUsuarioDAO.save(moduloUsuario);
	}

	public void delete(ModuloUsuario moduloUsuario) {
		moduloUsuarioDAO.delete(moduloUsuario);
	}

	public ModuloUsuario getById(Long idModuloUsuario) {
		return moduloUsuarioDAO.getById(idModuloUsuario);
	}

	public List<ModuloUsuario> getAll() {
		return moduloUsuarioDAO.getAll();
	}

	public List<ModuloUsuario> getByUsuario(Usuario usuario) {
		return moduloUsuarioDAO.getByUsuario(usuario);
	}
}
