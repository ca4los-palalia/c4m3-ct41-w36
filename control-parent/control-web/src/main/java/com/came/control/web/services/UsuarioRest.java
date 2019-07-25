package com.came.control.web.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;
import com.came.control.web.RestMetaclas;

@Repository
public class UsuarioRest extends RestMetaclas {
	
	private static final Logger logger = Logger.getLogger(UsuarioRest.class);
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public Usuario save(Usuario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_SAVE), gson.toJson(itemObject));
		return recuperadorObjectUsuario(response);
	}

	public void delete(Usuario itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_DELETE), gson.toJson(itemObject));
		logger.info(response);
	}

	
	public Usuario getById(Long idFinder, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ID_FINDER, idFinder);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_BY_ID), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectUsuario(response);
	}
	
	public List<Usuario> getAll() {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_ALL ), "*");
		return recuperadorListUsuario(response);
	}

	public List<Usuario> getByOrganizacion(Organizacion itemObject) {
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_BY_ORGANIZACION ), gson.toJson(itemObject));
		return recuperadorListUsuario(response);
	}

	public List<Usuario> getByEstatus(Estatus estatus, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ESTATUS, estatus);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_BY_ESTATUS), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorListUsuario(response);
	}

	public List<Usuario> getByOficina(Oficina oficina, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.OFICINA, oficina);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_BY_OFICINA), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorListUsuario(response);
	}

	public Usuario getAutenticaWithNip(String nip, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NIP, nip);
		map.put(ConstAtributos.ORGANIZACION, organizacion);
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_AUTENTICA_WITH_NIP), gson.toJson(ctrlUtilString.mapperToString(map)));
		return recuperadorObjectUsuario(response);
	}

	public Usuario getAutenticaWithWeb(String usr, String psw, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.NOMBRE_USUARIO, ctrlUtils.Encriptar(usr));
		map.put(ConstAtributos.CONTRASENIA, ctrlUtils.Encriptar(psw));
		map.put(ConstAtributos.ORGANIZACION, ctrlUtilString.encoderB64(gson.toJson(organizacion)));

		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_AUTENTICA_WITH_WEB), ctrlUtilString.mapperToString(map));
		return recuperadorObjectUsuario(response);
	}
	
	public List<Usuario> getByRol(Rol rol, Organizacion organizacion) {
		Map<String, Object> map = new HashMap<>();
		map.put(ConstAtributos.ROL, ctrlUtilString.encoderB64(gson.toJson(rol)));
		map.put(ConstAtributos.ORGANIZACION, ctrlUtilString.encoderB64(gson.toJson(organizacion)));
		
		String response = procesarRestFULL((cfg.getValor() + ConstMap.MAP_USUARIO_BY_ROL), ctrlUtilString.mapperToString(map));
		return recuperadorListUsuario(response);
	}
	
	private Usuario recuperadorObjectUsuario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToUsuario(response);
	}
	
	private List<Usuario> recuperadorListUsuario(String response) {
		if (response.contains(ConstAtributos.ERROR_EXCEPTION)) {
			logger.error(response);
			return null;
		}
		return createResponseToListUsuario(response);
	}
}
