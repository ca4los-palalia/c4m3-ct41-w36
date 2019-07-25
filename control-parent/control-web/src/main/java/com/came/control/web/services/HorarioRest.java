package com.came.control.web.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.web.RestMetaclas;

@Repository
public class HorarioRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(HorarioRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Horario save(Horario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectHorario(response);
	}

	public void delete(Horario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public Horario getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_BY_ID), idFinder.toString());
		return recuperadorObjectHorario(response);
	}

	public List<Horario> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_ALL), "*");
		return recuperadorListHorario(response);
	}

	public List<Horario> getByUsuario(Usuario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_BY_USUARIO), gson.toJson(itemObject));
		return recuperadorListHorario(response);
	}

	public List<Horario> getWithDescanso(boolean itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_WITH_DESCANSO),
				String.valueOf(itemObject));
		return recuperadorListHorario(response);
	}

	public List<Horario> getByZonaHorario(ZonaHorario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_HORARIO_BY_ZONA_HORARIO),
				gson.toJson(itemObject));
		return recuperadorListHorario(response);
	}
	
	private Horario recuperadorObjectHorario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToHorario(response);
	}
	
	private List<Horario> recuperadorListHorario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListHorario(response);
	}

}
