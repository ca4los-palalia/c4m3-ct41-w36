package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class IncidenciasRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(IncidenciasRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public Incidencia save(Incidencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_SAVE), gson.toJson(itemObject));
		return recuperadorObjectIncidencia(response);
	}

	public void delete(Incidencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public Incidencia getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion);

		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_BY_ID),
				gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectIncidencia(response);
	}

	public List<Incidencia> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_ALL), "*");
		return recuperadorListIncidencia(response);
	}

	public List<Incidencia> getByOrganizacion(Organizacion itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_BY_ORGANIZACION),
				gson.toJson(itemObject));
		return recuperadorListIncidencia(response);
	}
	
	public Incidencia getByNombre(String nombre, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NOMBRE, nombre);
		map.put(ConstAtributos.ORGANIZACION, organizacion);

		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_BY_NOMBRE),
				gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectIncidencia(response);
	}
	
	public Incidencia getByClave(String clave, Long IdOrg) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.CLAVE, clave);
		map.put(ConstAtributos.ORGANIZACION, IdOrg);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_INCIDENCIAS_BY_CLAVE),
				gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectIncidencia(response);
	}
	
	private Incidencia recuperadorObjectIncidencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToIncidencias(response);
	}
	
	private List<Incidencia> recuperadorListIncidencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListIncidencias(response);
	}
}