package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Modulo;

public abstract interface ModuloDAO {

	public abstract void save(Modulo modulo);

	public abstract void delete(Modulo modulo);

	public abstract Modulo getByNombre(String nombre);

	public abstract Modulo getByRuta(String ruta);

	public abstract Modulo getById(Long idModulo);

	public abstract List<Modulo> getAll();
}
