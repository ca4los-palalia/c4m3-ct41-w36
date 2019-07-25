package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Organizacion;
import com.came.control.web.RestMetaclas;

@Repository
public class BancoRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(BancoRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public Banco save(Banco entity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_SAVE), gson.toJson(entity));
		return recuperadorObjectBanco(response);
	}

	public void delete(Banco entity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_DELETE), gson.toJson(entity));
		logger.error(response);
	}

	public Banco getById(Long idEntity) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_BY_ID), idEntity.toString());
		return recuperadorObjectBanco(response);
	}

	public Banco getByNombre(String nombreBanco, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NOMBRE, nombreBanco);
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_BY_NOMBRE), gson.toJson(maperString));
		return recuperadorObjectBanco(response);
	}
	
	public Banco getByClave(String clave, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.CLAVE, clave);
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_BY_CLAVE), gson.toJson(maperString));
		return recuperadorObjectBanco(response);
	}
	
	public Banco getByRfc(String rfc, Organizacion org) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.RFC, rfc);
		map.put(ConstAtributos.ORGANIZACION, org.getIdOrganizacion());
		
		String maperString = ctrlUtilString.mapperToString(map);
		if(maperString.contains(ConstAtributos.ERROR_EXCEPTION)){
			logger.error(maperString);
			return null;
		}
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_BY_RFC), gson.toJson(maperString));
		return recuperadorObjectBanco(response);
	}

	public List<Banco> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_ALL ), "*");
		return recuperadorListBanco(response);
	}

	public List<Banco> getAllByOrganizacion(Organizacion org) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_BANCO_ALL_BY_ORG ), org.getIdOrganizacion().toString());
		return recuperadorListBanco(response);
	}
	
	
	
	private Banco recuperadorObjectBanco(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToBanco(response);
	}
	
	private List<Banco> recuperadorListBanco(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListBanco(response);
	}
}
