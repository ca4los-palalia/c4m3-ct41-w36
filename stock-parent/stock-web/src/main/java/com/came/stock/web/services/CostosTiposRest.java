package com.came.stock.web.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.Organizacion;

@Repository
public class CostosTiposRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public CtrlRestService save(CostosTipos itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_TIPOS_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCostosTipos(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(CostosTipos itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_TIPOS_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("idFinder", idFinder);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_TIPOS_BY_ID), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCostosTipos(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll(boolean limitarOrganizacion, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("limitarOrganizacion", limitarOrganizacion);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_TIPOS_ALL), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListCostosTipos(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
