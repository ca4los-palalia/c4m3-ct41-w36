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
import com.came.control.model.domain.Persona;

@RestController
public class PersonaController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_PERSONA_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Persona itemProcess = gson.fromJson(jsonItem, Persona.class);
		personaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_PERSONA_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Persona itemProcess = gson.fromJson(jsonItem, Persona.class);
		personaService.delete(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_PERSONA_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Persona itemProcess = personaService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Persona());
	}

	@PostMapping({ ConstMap.MAP_PERSONA_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Persona> list = personaService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Persona>());
	}

}
