package com.came.control.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.control.beans.constantes.ConstMap;
import com.came.control.core.ControllerMetaclas;
import com.came.control.model.domain.Modulo;

@RestController
public class ModuloController extends ControllerMetaclas {
	
	@PostMapping({ ConstMap.MAP_MODULO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) {
		Modulo itemProcess = gson.fromJson(jsonItem, Modulo.class);
		moduloService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_MODULO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) {
		Modulo itemProcess = gson.fromJson(jsonItem, Modulo.class);
		moduloService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_MODULO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) {
		Modulo itemProcess = moduloService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Modulo());
	}
	
	@PostMapping({ ConstMap.MAP_MODULO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) {
		List<Modulo> list = moduloService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Modulo>());
	}
	
	@PostMapping({ ConstMap.MAP_MODULO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) {
		Modulo itemProcess = moduloService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Modulo());
	}

	@PostMapping({ ConstMap.MAP_MODULO_BY_RUTA })
	@Produces({ "application/json" })
	public String getByRuta(@RequestBody String jsonItem) {
		Modulo itemProcess = moduloService.getByRuta(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Modulo());
	}

	

	
}
