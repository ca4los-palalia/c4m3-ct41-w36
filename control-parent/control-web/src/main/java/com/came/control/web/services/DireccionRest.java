package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;
import com.came.control.web.RestMetaclas;

@Repository
public class DireccionRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Direccion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_SAVE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToDireccion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Direccion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_DELETE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_BY_ID), idFinder.toString());
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToDireccion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_ALL), "*");
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListDireccion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByCp(String itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_BY_CP), itemObject);
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListDireccion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByEstado(Estado itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_DIRECCION_BY_ESTADO),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListDireccion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
