package com.came.control.web.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class CalendariosRest extends RestMetaclas {
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Calendarios itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CALENDARIOS_SAVE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToCalendarios(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Calendarios itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CALENDARIOS_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
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
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CALENDARIOS_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCalendarios(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CALENDARIOS_ALL ), "*");
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListCalendarios(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByOrganizacion(Organizacion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_CALENDARIOS_BY_ORGANIZACION ), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListCalendarios(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
