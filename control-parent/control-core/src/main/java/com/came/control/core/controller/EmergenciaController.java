package com.came.control.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.control.beans.constantes.ConstMap;
import com.came.control.core.ControllerMetaclas;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Persona;

@RestController
public class EmergenciaController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_EMERGENCIA_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Emergencia itemProcess = gson.fromJson(jsonItem, Emergencia.class);
		emergenciaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_EMERGENCIA_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Emergencia itemProcess = gson.fromJson(jsonItem, Emergencia.class);
		emergenciaService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_EMERGENCIA_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Emergencia itemProcess = emergenciaService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Emergencia());
	}

	@PostMapping({ ConstMap.MAP_EMERGENCIA_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Emergencia> list = emergenciaService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Emergencia>());
	}

	@PostMapping({ ConstMap.MAP_EMERGENCIA_BY_PERSONA })
	@Produces({ "application/json" })
	public String getByPersona(@RequestBody String jsonItem) throws ParseException {
		Persona objectFinder = gson.fromJson(jsonItem, Persona.class);
		Emergencia itemProcess = emergenciaService.getByPersona(objectFinder);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Emergencia());
	}

}
