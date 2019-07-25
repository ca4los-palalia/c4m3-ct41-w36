package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.web.RestMetaclas;

@Repository
public class GrupoSanguineoRest extends RestMetaclas {
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(GrupoSanguineo itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GRUPO_SANGUINEO_SAVE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToGrupoSanguineo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(GrupoSanguineo itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GRUPO_SANGUINEO_DELETE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GRUPO_SANGUINEO_BY_ID), idFinder.toString());
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToGrupoSanguineo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GRUPO_SANGUINEO_ALL), "*");
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListGrupoSanguineo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByNombre(String itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GRUPO_SANGUINEO_BY_NOMBRE), itemObject);
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToGrupoSanguineo(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
