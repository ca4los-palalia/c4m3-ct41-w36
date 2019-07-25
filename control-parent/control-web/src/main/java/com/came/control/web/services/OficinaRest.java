package com.came.control.web.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class OficinaRest extends RestMetaclas {@PostConstruct public void init() {super.init(); }

public CtrlRestService save(Oficina itemObject) {
	CtrlRestService itemReturn = new CtrlRestService();
	String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_SAVE), gson.toJson(itemObject));
	if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
		itemReturn = createResponseToOficina(response);
	} else
		itemReturn.setResponse(response);
	return itemReturn;
}

public CtrlRestService delete(Oficina itemObject) {
	CtrlRestService itemReturn = new CtrlRestService();
	String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_DELETE), gson.toJson(itemObject));
	if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
		itemReturn.setSingle(response);
	else
		itemReturn.setResponse(response);
	return itemReturn;
}

public CtrlRestService getById(Long idFinder, Oficina organizacion) {
	Map<String, Object> map = new HashMap<>();
	map.put(ConstAtributos.ID_FINDER, idFinder);
	map.put(ConstAtributos.ORGANIZACION, organizacion);
	
	CtrlRestService itemReturn = new CtrlRestService();
	String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
	if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
		itemReturn = createResponseToOficina(response);
	} else
		itemReturn.setResponse(response);
	return itemReturn;
}


public CtrlRestService getAll() {
	CtrlRestService itemReturn = new CtrlRestService();
	String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_ALL ), "*");
	if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
		itemReturn = createResponseToListOficina(response);
	} else
		itemReturn.setResponse(response);
	return itemReturn;
}

	public CtrlRestService getByOrganizacion(Organizacion itemObject)  {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_BY_ORGANIZACION ), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListOficina(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByDireccion(Direccion direccion, Oficina organizacion)  {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.DIRECCION, direccion);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_OFICINA_BY_DIRECCION), gson.toJson(ctrlUtilString.mapperToString(map)));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToOficina(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
