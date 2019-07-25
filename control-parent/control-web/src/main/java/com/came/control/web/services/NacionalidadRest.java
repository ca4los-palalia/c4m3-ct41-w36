package com.came.control.web.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.web.RestMetaclas;

@Repository
public class NacionalidadRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(NacionalidadRest.class);
	
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Nacionalidad save(Nacionalidad itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_SAVE), gson.toJson(itemObject));
		return recuperadorObjectNacionalidad(response);
	}

	public void delete(Nacionalidad itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_DELETE), gson.toJson(itemObject));
		logger.error(response);
	}

	public Nacionalidad getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_BY_ID), idFinder.toString());
		return recuperadorObjectNacionalidad(response);
	}

	public List<Nacionalidad> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_ALL ), "*");
		return recuperadorListNacionalidad(response);
	}

	public Nacionalidad getByIdCodigoPais(String codigo) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_BY_CODIGO_PAIS), codigo);
		return recuperadorObjectNacionalidad(response);
	}

	public Nacionalidad getByNombre(String nombreNacionalidad) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_NACIONALIDAD_BY_NOMBRE), nombreNacionalidad);
		return recuperadorObjectNacionalidad(response);
	}

	public Nacionalidad getByClave(String claveNacionalidad) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_ID), claveNacionalidad);
		return recuperadorObjectNacionalidad(response);
	}
	
	private Nacionalidad recuperadorObjectNacionalidad(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToNacionalidad(response);
	}
	
	private List<Nacionalidad> recuperadorListNacionalidad(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListNacionalidad(response);
	}

}
