package com.came.control.web.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;
import com.came.control.web.RestMetaclas;

@Repository
public class DatosGeneralesRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(DatosGenerales itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_SAVE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(DatosGenerales itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_DELETE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_ALL ), "*");
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByOrganizacion(Organizacion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_ORGANIZACION ), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	
	public CtrlRestService getByPersona(Persona persona, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.PERSONA, persona);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_PERSONA), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByRfc(String rfc, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.RFC, rfc);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_RFC), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByNss(String nss, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NSS, nss);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_NSS), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByCurp(String curp, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.CURP, curp);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_CURP), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByEstadoCivil(EstadoCivil estadoCivil, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ESTADO_CIVIL, estadoCivil);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DATOS_GENERALES_BY_ESTADO_CIVIL), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToDatosGenerales(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
