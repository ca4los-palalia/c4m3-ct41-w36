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
import com.came.control.model.domain.Sexo;

@RestController
public class SexoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_SEXO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Sexo itemProcess = gson.fromJson(jsonItem, Sexo.class);
		sexoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_SEXO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Sexo itemProcess = gson.fromJson(jsonItem, Sexo.class);
		sexoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_SEXO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Sexo itemProcess = sexoService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Sexo());
	}

	@PostMapping({ ConstMap.MAP_SEXO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Sexo> list = sexoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Sexo>());
	}

	@PostMapping({ ConstMap.MAP_SEXO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Sexo itemProcess = sexoService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Sexo());
	}

}
