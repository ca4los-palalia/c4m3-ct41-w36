package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;

public abstract interface UsuarioDAO {

	public abstract void save(Usuario entity);

	public abstract void delete(Usuario entity);

	public abstract Usuario getById(Long idEntity, Organizacion organizacion);

	public abstract List<Usuario> getAll();
	
	public abstract List<Usuario> getByOrganizacion(Organizacion organizacion);
	
	public abstract List<Usuario> getByEstatus(Estatus estatus, Organizacion organizacion);
	
	public abstract List<Usuario> getByOficina(Oficina oficina, Organizacion organizacion);
	
	public abstract List<Usuario> getByRol(Rol rol, Organizacion organizacion);
	
	public abstract Usuario getAutenticacWithNip(String nip, Organizacion organizacion);
	
	public abstract Usuario getAutenticacWithWeb(String usuario, String contrasenia, Organizacion organizacion);
}
