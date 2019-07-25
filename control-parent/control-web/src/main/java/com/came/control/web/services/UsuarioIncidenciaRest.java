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
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;
import com.came.control.web.RestMetaclas;

@Repository
public class UsuarioIncidenciaRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(UsuarioIncidenciaRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public UsuarioIncidencia save(UsuarioIncidencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_SAVE), gson.toJson(itemObject));
		return recuperadorObjectUsuarioIncidencia(response);
	}

	public void delete(UsuarioIncidencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_DELETE), gson.toJson(itemObject));
		logger.error(response);
	}

	public UsuarioIncidencia getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectUsuarioIncidencia(response);
	}
	
	public List<UsuarioIncidencia> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_ALL ), "*");
		return recuperadorListUsuarioIncidencia(response);
	}

	public List<UsuarioIncidencia> getByOrganizacion(Organizacion itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_BY_ORGANIZACION ), gson.toJson(itemObject));
		return recuperadorListUsuarioIncidencia(response);
	}

	public List<UsuarioIncidencia> getByUsuario(Usuario usr, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.USUARIO, usr.getIdUsuario());
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_BY_USUARIO), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorListUsuarioIncidencia(response);
	}

	public List<UsuarioIncidencia> getByUsuarioAndFecha(Usuario usr, Organizacion org, Date fecha) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fecha), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		map.put(ConstAtributos.USUARIO, usr.getIdUsuario());
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_BY_USUARIO_AND_FECHA), gson.toJson(maperString));
		return recuperadorListUsuarioIncidencia(response);
	}
	
	public List<UsuarioIncidencia> getByIncidenciaAndFecha(Incidencia incidencia, Organizacion org, Date fecha) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fecha), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		map.put(ConstAtributos.INCIDENCIA, incidencia);
		map.put(ConstAtributos.ORGANIZACION, org);
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_INCIDENCIA_BY_INCIDENCIA_AND_FECHA), gson.toJson(maperString));
		return recuperadorListUsuarioIncidencia(response);
	}
	
	private UsuarioIncidencia recuperadorObjectUsuarioIncidencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToUsuarioIncidencia(response);
	}
	
	private List<UsuarioIncidencia> recuperadorListUsuarioIncidencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListUsuarioIncidencia(response);
	}
}
