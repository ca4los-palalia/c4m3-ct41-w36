package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class EscolaridadRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(EscolaridadRest.class);
	
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Escolaridad save(Escolaridad itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_SAVE), gson.toJson(itemObject));
		return recuperadorObjectEscolaridad(response);
	}

	public void delete(Escolaridad itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_DELETE), gson.toJson(itemObject));
		logger.error(response);
	}

	public Escolaridad getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_BY_ID), idFinder.toString());
		return recuperadorObjectEscolaridad(response);
	}

	public Escolaridad getByNombre(String nombreEscolaridad, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NOMBRE, nombreEscolaridad);
		map.put(ConstAtributos.ORGANIZACION, org);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_BY_NOMBRE_AND_ORG), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectEscolaridad(response);
	}
	
	public List<Escolaridad> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_ALL ), "*");
		return recuperadorListEscolaridad(response);
	}
	
	public List<Escolaridad> getAllByOrganizacion(Organizacion org) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESCOLARIDAD_ALL_BY_ORG ), org.getIdOrganizacion().toString());
		return recuperadorListEscolaridad(response);
	}

	private Escolaridad recuperadorObjectEscolaridad(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToEscolaridad(response);
	}
	
	private List<Escolaridad> recuperadorListEscolaridad(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListEscolaridad(response);
	}

}
