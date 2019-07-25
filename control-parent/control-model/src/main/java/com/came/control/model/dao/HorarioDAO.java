package com.came.control.model.dao;

import java.util.List;

import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;

public abstract interface HorarioDAO {

	public abstract void save(Horario entity);

	public abstract void delete(Horario entity);

	public abstract Horario getById(Long idEntity);

	public abstract List<Horario> getAll();
	
	public abstract List<Horario> getByUsuario(Usuario usuario);

	public abstract List<Horario> getWithDescanso(boolean descanso);
	
	public abstract List<Horario> getByZonaHorario(ZonaHorario zonaHorario);
}
