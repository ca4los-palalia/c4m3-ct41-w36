package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Pais;
import com.came.control.web.RestMetaclas;

@Repository
public class EstadoRest extends RestMetaclas {
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Estado itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_SAVE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Estado itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_DELETE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_BY_ID), idFinder.toString());
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_ALL), "*");
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByNombre(String itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_BY_NOMBRE), itemObject);
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getByAbreviatura(String itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_BY_ABREVIATURA), itemObject);
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getByCapital(String itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_BY_CAPITAL), itemObject);
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByPais(Pais itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_ESTADO_BY_PAIS), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListEstado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
