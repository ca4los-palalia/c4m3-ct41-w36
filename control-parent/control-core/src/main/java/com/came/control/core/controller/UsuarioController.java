package com.came.control.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.core.ControllerMetaclas;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;
import com.google.gson.JsonSyntaxException;

@RestController
public class UsuarioController extends ControllerMetaclas {

	private static final Logger logger = Logger.getLogger(UsuarioController.class);
	
	@PostMapping({ ConstMap.MAP_USUARIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Usuario itemProcess = gson.fromJson(jsonItem, Usuario.class);
		usuarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_USUARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Usuario itemProcess = gson.fromJson(jsonItem, Usuario.class);
		usuarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_USUARIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Usuario itemProcess = usuarioService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Usuario());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Usuario> list = usuarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Usuario>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Usuario> list = usuarioService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Usuario>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_BY_ESTATUS })
	@Produces({ "application/json" })
	public String getByEstatus(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		List<Usuario> list = usuarioService.getByEstatus(((Estatus) map.get(ConstAtributos.ESTATUS)),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Usuario>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_BY_OFICINA })
	@Produces({ "application/json" })
	public String getByOficina(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		List<Usuario> list = usuarioService.getByOficina(((Oficina) map.get(ConstAtributos.OFICINA)),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Usuario>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_AUTENTICA_WITH_NIP })
	@Produces({ "application/json" })
	public String getAutenticaWithNip(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Usuario usr = usuarioService.getAutenticacWithNip(map.get(ConstAtributos.NIP).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return usr != null ? gson.toJson(usr) : gson.toJson(new Usuario());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_AUTENTICA_WITH_WEB })
	@Produces({ "application/json" })
	public String getAutenticaWithWeb(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Organizacion org = gson.fromJson(ctrlUtilString.decoderB64(map.get(ConstAtributos.ORGANIZACION).toString()), Organizacion.class);
		
		Usuario usr = usuarioService.getAutenticacWithWeb(map.get(ConstAtributos.NOMBRE_USUARIO).toString(),
				map.get(ConstAtributos.CONTRASENIA).toString(),
				org);
		
		String response = "";
		try {
			response = usr != null ? gson.toJson(usr) : gson.toJson(new Usuario());
		} catch (JsonSyntaxException   e) {
			logger.error(e.getMessage(), e);
		}
		return response;
	}
	
	
	@PostMapping({ ConstMap.MAP_USUARIO_BY_ROL})
	@Produces({ "application/json" })
	public String getByRol(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Rol rol = gson.fromJson(ctrlUtilString.decoderB64(map.get(ConstAtributos.ROL).toString()), Rol.class);
		Organizacion org = gson.fromJson(ctrlUtilString.decoderB64(map.get(ConstAtributos.ORGANIZACION).toString()), Organizacion.class);
		
		List<Usuario> list = usuarioService.getByRol(rol, org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Usuario>());
	}

}
