package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.HorarioDAO;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;

@Service
public class HorarioService {

	@Autowired
	private HorarioDAO horarioDAO;

	public void save(Horario entity) {
		horarioDAO.save(entity);
	}

	public void delete(Horario entity) {
		horarioDAO.delete(entity);
	}

	public Horario getById(Long idEntity) {
		return horarioDAO.getById(idEntity);
	}

	public List<Horario> getAll() {
		return horarioDAO.getAll();
	}

	public List<Horario> getByUsuario(Usuario usuario){
		return horarioDAO.getByUsuario(usuario);
	}
	
	public List<Horario> getWithDescanso(boolean descanso) {
		return horarioDAO.getWithDescanso(descanso);
	}

	public List<Horario> getByZonaHorario(ZonaHorario zonaHorario) {
		return horarioDAO.getByZonaHorario(zonaHorario);
	}
}
