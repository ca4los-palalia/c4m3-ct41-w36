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
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class AsistenciaRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(AsistenciaRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public Asistencia save(Asistencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_SAVE), gson.toJson(itemObject));
		return recuperadorObjectAsistencia(response);
	}

	public void delete(Asistencia itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_DELETE), gson.toJson(itemObject));
		logger.error(response);
	}

	
	public Asistencia getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectAsistencia(response);
	}
	
	public List<Asistencia> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_ALL ), "*");
		return recuperadorListAsistencia(response);
	}

	public List<Asistencia> getByOrganizacion(Organizacion itemObject, Date fecha) {
		String fechaString = fecha == null ? "*" : ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fecha), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, fechaString);
		map.put(ConstAtributos.ORGANIZACION, itemObject.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_ORGANIZACION ), gson.toJson(maperString));
		return recuperadorListAsistencia(response);
	}

	public List<Asistencia> getByFinalizados(boolean finalizado, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FINALIZADO, finalizado);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_FINALIZADOS), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorListAsistencia(response);
	}

	public Asistencia getByUsrAndDate(Date fecha, Long idUsr, Long idOrganizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fecha), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		map.put(ConstAtributos.USUARIO, idUsr);
		map.put(ConstAtributos.ORGANIZACION, idOrganizacion);
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_USR_AND_DATE), gson.toJson(maperString));
		return recuperadorObjectAsistencia(response);
	}
	
	
	public List<Asistencia> getByUsrAndDateWeek(Date fechaInicio, Date fechaFin, Long idUsr, Long idOrganizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fechaInicio), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		map.put(ConstAtributos.FECHA_FIN, ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fechaFin), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		map.put(ConstAtributos.USUARIO, idUsr);
		map.put(ConstAtributos.ORGANIZACION, idOrganizacion);
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_USR_AND_DATE_WEEK), gson.toJson(maperString));
		return recuperadorListAsistencia(response);
	}

	public List<Asistencia> getByFecha(Date fecha, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.FECHA, fecha);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ASISTENCIA_BY_FECHA), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorListAsistencia(response);
	}
	
	private Asistencia recuperadorObjectAsistencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToAsistencia(response);
	}
	
	private List<Asistencia> recuperadorListAsistencia(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListAsistencia(response);
	}
}
