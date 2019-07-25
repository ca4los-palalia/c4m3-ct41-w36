package com.came.control.web.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Modulo;
import com.came.control.web.RestMetaclas;

@Repository
public class ModuloRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(ModuloRest.class);
	
	public Modulo save(Modulo itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectModulo(response);
	}

	public void delete(Modulo itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public Modulo getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_BY_ID), idFinder.toString());
		return recuperadorObjectModulo(response);
	}
	
	public List<Modulo> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_ALL), "*");
		return recuperadorListModulo(response);
	}
	
	public Modulo getByNombre(String jsonItem) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_BY_NOMBRE), jsonItem);
		return recuperadorObjectModulo(response);
	}

	public Modulo getByRuta(String ruta) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_BY_RUTA), ruta);
		return recuperadorObjectModulo(response);
	}
	
	
	
	
	
	private Modulo recuperadorObjectModulo(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToModulo(response);
	}
	
	private List<Modulo> recuperadorListModulo(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListModulo(response);
	}
}
