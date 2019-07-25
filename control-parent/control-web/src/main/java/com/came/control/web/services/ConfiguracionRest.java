package com.came.control.web.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class ConfiguracionRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(ConfiguracionRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public Configuracion save(Configuracion itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_SAVE), gson.toJson(itemObject));
		return recuperadorObjectConfiguracion(response);
	}

	public void delete(Configuracion itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_DELETE), gson.toJson(itemObject));
		logger.error(response);
	}

	
	public Configuracion getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion.getIdOrganizacion());
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectConfiguracion(response);
	}
	
	public List<Configuracion> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_ALL ), "*");
		return recuperadorListConfiguracion(response);
	}

	public List<Configuracion> getByOrganizacion(Organizacion itemObject, Date fecha) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ORGANIZACION, itemObject.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_BY_ORGANIZACION ), gson.toJson(maperString));
		return recuperadorListConfiguracion(response);
	}

	public Configuracion getByClaveAndOrg(String clave, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.CLAVE, clave);
		map.put(ConstAtributos.ORGANIZACION, organizacion.getIdOrganizacion());
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CONFIGURACION_BY_CLAVE_AND_ORGANIZACION), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectConfiguracion(response);
	}
	
	
	
	
	private Configuracion recuperadorObjectConfiguracion(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToConfiguracion(response);
	}
	
	private List<Configuracion> recuperadorListConfiguracion(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListConfiguracion(response);
	}
}
