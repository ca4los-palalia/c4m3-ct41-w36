package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Usuario;

public abstract interface ModuloUsuarioDAO {
	public abstract void save(ModuloUsuario moduloUsuario);

	public abstract void delete(ModuloUsuario moduloUsuario);

	public abstract ModuloUsuario getById(Long idModuloUsuario);

	public abstract List<ModuloUsuario> getAll();

	public abstract List<ModuloUsuario> getByUsuario(Usuario usuario);
}
