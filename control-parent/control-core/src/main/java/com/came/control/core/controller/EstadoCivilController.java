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
import com.came.control.model.domain.EstadoCivil;

@RestController
public class EstadoCivilController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ESTADO_CIVIL_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		EstadoCivil itemProcess = gson.fromJson(jsonItem, EstadoCivil.class);
		estadoCivilService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ESTADO_CIVIL_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		EstadoCivil itemProcess = gson.fromJson(jsonItem, EstadoCivil.class);
		estadoCivilService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ESTADO_CIVIL_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		EstadoCivil itemProcess = estadoCivilService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new EstadoCivil());
	}

	@PostMapping({ ConstMap.MAP_ESTADO_CIVIL_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<EstadoCivil> list = estadoCivilService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<EstadoCivil>());
	}

	@PostMapping({ ConstMap.MAP_ESTADO_CIVIL_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		EstadoCivil itemProcess = estadoCivilService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new EstadoCivil());
	}

}
