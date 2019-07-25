package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.web.RestMetaclas;

@Repository
public class ZonaHorarioRest extends RestMetaclas {

	private static final Logger logger = Logger.getLogger(ZonaHorarioRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public ZonaHorario save(ZonaHorario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectZonaHorario(response);
	}

	public void delete(ZonaHorario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public ZonaHorario getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_BY_ID), idFinder.toString());
		return recuperadorObjectZonaHorario(response);
	}
	
	public ZonaHorario getByZonaHorario(String zonaHoraria, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ZONA_HORARIA, zonaHoraria);
		map.put(ConstAtributos.ORGANIZACION, ctrlUtilString.encoderB64(gson.toJson(organizacion)));
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_BY_ZONA_HORARIO), ctrlUtilString.mapperToString(map));
		return recuperadorObjectZonaHorario(response);
	}

	public List<ZonaHorario> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_ALL), "*");
		return recuperadorListZonaHorarios(response);
	}
	
	public List<ZonaHorario> getByOrganizacion(Organizacion org) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ZONA_HORARIO_BY_ORGANIZACION), gson.toJson(org));
		return recuperadorListZonaHorarios(response);
	}
	
	private ZonaHorario recuperadorObjectZonaHorario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToZonaHorario(response);
	}
	
	private List<ZonaHorario> recuperadorListZonaHorarios(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListZonaHorario(response);
	}
	
}