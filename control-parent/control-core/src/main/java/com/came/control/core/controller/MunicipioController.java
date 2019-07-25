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
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Municipio;

@RestController
public class MunicipioController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_MUNICIPIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Municipio itemProcess = gson.fromJson(jsonItem, Municipio.class);
		municipioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_MUNICIPIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Municipio itemProcess = gson.fromJson(jsonItem, Municipio.class);
		municipioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_MUNICIPIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Municipio itemProcess = municipioService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Municipio());
	}

	@PostMapping({ ConstMap.MAP_MUNICIPIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Municipio> list = municipioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Municipio>());
	}

	@PostMapping({ ConstMap.MAP_MUNICIPIO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Municipio itemProcess = municipioService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Municipio());
	}
	

	@PostMapping({ ConstMap.MAP_MUNICIPIO_BY_ESTADO })
	@Produces({ "application/json" })
	public String getByPais(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = gson.fromJson(jsonItem, Estado.class);
		List<Municipio> list = municipioService.getByEstado(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Municipio>());
	}

}
