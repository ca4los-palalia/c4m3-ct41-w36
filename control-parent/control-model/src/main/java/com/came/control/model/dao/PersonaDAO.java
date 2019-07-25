package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Persona;

public abstract interface PersonaDAO {

	public abstract void save(Persona entity);

	public abstract void delete(Persona entity);

	public abstract Persona getById(Long idEntity);

	public abstract List<Persona> getAll();
}
