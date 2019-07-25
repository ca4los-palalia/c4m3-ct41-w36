package com.came.control.web.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Municipio;
import com.came.control.web.RestMetaclas;

@Repository
public class MunicipioRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(MunicipioRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Municipio save(Municipio itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectMunicipio(response);
	}

	public void delete(Municipio itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public Municipio getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_BY_ID), idFinder.toString());
		return recuperadorObjectMunicipio(response);
	}

	public List<Municipio> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_ALL), "*");
		return recuperadorListMunicipio(response);
	}

	public Municipio getByNombre(String itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_BY_NOMBRE), itemObject);
		return recuperadorObjectMunicipio(response);
	}

	public List<Municipio> getByEstado(Estado itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MUNICIPIO_BY_ESTADO), gson.toJson(itemObject));
		return recuperadorListMunicipio(response);
	}
	
	private Municipio recuperadorObjectMunicipio(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToMunicipio(response);
	}
	
	private List<Municipio> recuperadorListMunicipio(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListMunicipio(response);
	}

}
