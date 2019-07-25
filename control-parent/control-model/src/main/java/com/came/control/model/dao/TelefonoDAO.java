package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Telefono;

public abstract interface TelefonoDAO {

	public abstract void save(Telefono entity);

	public abstract void delete(Telefono entity);

	public abstract Telefono getById(Long idEntity);

	public abstract List<Telefono> getAll();
}
