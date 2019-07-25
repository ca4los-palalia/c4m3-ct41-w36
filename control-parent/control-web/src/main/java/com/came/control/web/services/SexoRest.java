package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Sexo;
import com.came.control.web.RestMetaclas;

@Repository
public class SexoRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Sexo itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_SEXO_SAVE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToSexo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Sexo itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_SEXO_DELETE), gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_SEXO_BY_ID), idFinder.toString());
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToSexo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_SEXO_ALL ), "*");
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListSexo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByNombre(String nombre) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_SEXO_BY_NOMBRE), nombre);
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToSexo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
