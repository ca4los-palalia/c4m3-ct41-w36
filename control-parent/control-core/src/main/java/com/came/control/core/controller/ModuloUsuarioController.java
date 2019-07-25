package com.came.control.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.core.ControllerMetaclas;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

@RestController
public class ModuloUsuarioController extends ControllerMetaclas {
	

	@PostMapping({ ConstMap.MAP_MODULO_USUARIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) {
		ModuloUsuario itemProcess = gson.fromJson(jsonItem, ModuloUsuario.class);
		moduloUsuarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_MODULO_USUARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) {
		ModuloUsuario itemProcess = gson.fromJson(jsonItem, ModuloUsuario.class);
		moduloUsuarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_MODULO_USUARIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) {
		ModuloUsuario itemProcess = moduloUsuarioService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new ModuloUsuario());
	}

	@PostMapping({ ConstMap.MAP_MODULO_USUARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) {
		List<ModuloUsuario> list = moduloUsuarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ModuloUsuario>());
	}

	@PostMapping({ ConstMap.MAP_MODULO_USUARIO_BY_USUARIO })
	@Produces({ "application/json" })
	public String getByUsuario(@RequestBody String jsonItem) {
		Usuario itemProcess = gson.fromJson(ctrlUtilString.decoderB64(jsonItem), Usuario.class);
		List<ModuloUsuario> list = moduloUsuarioService.getByUsuario(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ModuloUsuario>());
	}
}
