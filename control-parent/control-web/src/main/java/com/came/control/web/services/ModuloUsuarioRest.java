package com.came.control.web.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Usuario;
import com.came.control.web.RestMetaclas;

@Repository
public class ModuloUsuarioRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(ModuloUsuarioRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public ModuloUsuario save(ModuloUsuario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_USUARIO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectModuloUsuario(response);
	}

	public void delete(ModuloUsuario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_USUARIO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	public ModuloUsuario getById(Long idFinder) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_USUARIO_BY_ID), idFinder.toString());
		return recuperadorObjectModuloUsuario(response);
	}

	public List<ModuloUsuario> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_USUARIO_ALL), "*");
		return recuperadorListModuloUsuario(response);
	}

	public List<ModuloUsuario> getByUsuario(Usuario usuario) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_MODULO_USUARIO_BY_USUARIO), ctrlUtilString.encoderB64(gson.toJson(usuario)));
		return recuperadorListModuloUsuario(response);
	}
	
	private ModuloUsuario recuperadorObjectModuloUsuario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToModuloUsuario(response);
	}
	
	private List<ModuloUsuario> recuperadorListModuloUsuario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListModuloUsuario(response);
	}
}
