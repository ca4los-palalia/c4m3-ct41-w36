package com.came.control.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class GeolocalizacionRest extends RestMetaclas {
	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Geolocalizacion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GEOLOCALIZACION_SAVE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToGeolocalizacion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(Geolocalizacion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GEOLOCALIZACION_DELETE),
				gson.toJson(itemObject));
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GEOLOCALIZACION_BY_ID), idFinder.toString());
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToGeolocalizacion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_GEOLOCALIZACION_ALL), "*");
		if (!response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			itemReturn = createResponseToListGeolocalizacion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
