package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Politicas;
import com.came.control.web.RestMetaclas;

@Repository
public class PoliticasRest extends RestMetaclas {

	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Politicas itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_POLITICAS_SAVE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToPoliticas(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Politicas itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_POLITICAS_DELETE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_POLITICAS_BY_ID), idFinder.toString());
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToPoliticas(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_POLITICAS_ALL), "*");
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListPoliticas(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByPeriodo(Periodo itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_POLITICAS_BY_PERIODO),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListPoliticas(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
