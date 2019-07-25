package com.came.control.web.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.web.RestMetaclas;

@Repository
public class RolRest extends RestMetaclas {
	private static final Logger logger = Logger.getLogger(RolRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Rol save(Rol itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_SAVE), gson.toJson(itemObject));
		return recuperadorObjectRol(response);
	}

	public void delete(Rol itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public Rol getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_BY_ID), idFinder.toString());
		return recuperadorObjectRol(response);
	}

	public List<Rol> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_ALL), "*");
		return recuperadorListRol(response);
	}

	public Rol getByNombre(String itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_BY_NOMBRE), itemObject);
		return recuperadorObjectRol(response);
	}
	
	public List<Rol> getByOrganizacion(Organizacion itemProcess) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ROL_BY_ORGANIZACION), gson.toJson(itemProcess));
		return recuperadorListRol(response);
	}
	
	private Rol recuperadorObjectRol(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToRol(response);
	}
	
	private List<Rol> recuperadorListRol(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListRol(response);
	}
}
