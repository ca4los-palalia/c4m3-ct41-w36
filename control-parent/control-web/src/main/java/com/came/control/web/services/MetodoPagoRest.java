package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class MetodoPagoRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(MetodoPagoRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public MetodoPago save(MetodoPago entity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_SAVE), gson.toJson(entity));
		return recuperadorObjectMetodoPago(response);
	}

	public void delete(MetodoPago entity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_DELETE), gson.toJson(entity));
		logger.error(response);
	}

	public MetodoPago getById(Long idEntity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_BY_ID), idEntity.toString());
		return recuperadorObjectMetodoPago(response);
	}

	public MetodoPago getByNombre(String nombreMetodoPago, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NOMBRE, nombreMetodoPago);
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_BY_NOMBRE), gson.toJson(maperString));
		return recuperadorObjectMetodoPago(response);
	}
	
	public List<MetodoPago> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_ALL ), "*");
		return recuperadorListMetodoPago(response);
	}

	public List<MetodoPago> getAllByOrganizacion(Organizacion org) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_METODO_PAGO_ALL_BY_ORG ), org.getIdOrganizacion().toString());
		return recuperadorListMetodoPago(response);
	}
	
	
	
	private MetodoPago recuperadorObjectMetodoPago(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToMetodoPago(response);
	}
	
	private List<MetodoPago> recuperadorListMetodoPago(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListMetodoPago(response);
	}
}
