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
import com.came.control.model.domain.Nacionalidad;

@RestController
public class NacionalidadController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Nacionalidad entity = gson.fromJson(jsonItem, Nacionalidad.class);
		nacionalidadService.save(entity);
		return gson.toJson(entity);
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Nacionalidad entity = gson.fromJson(jsonItem, Nacionalidad.class);
		nacionalidadService.delete(entity);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Nacionalidad entity = nacionalidadService.getById(new Long(jsonItem));
		return entity != null ? gson.toJson(entity) : gson.toJson(new Nacionalidad());
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Nacionalidad> list = nacionalidadService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Nacionalidad>());
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_BY_CODIGO_PAIS })
	@Produces({ "application/json" })
	public String getByIdCodigoPais(@RequestBody String jsonItem) throws ParseException {
		Nacionalidad entity = nacionalidadService.getByIdCodigoPais(jsonItem);
		return entity != null ? gson.toJson(entity) : gson.toJson(new Nacionalidad());
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Nacionalidad entity = nacionalidadService.getByNombre(jsonItem);
		return entity != null ? gson.toJson(entity) : gson.toJson(new Nacionalidad());
	}

	@PostMapping({ ConstMap.MAP_NACIONALIDAD_BY_CLAVE })
	@Produces({ "application/json" })
	public String getByClave(String claveNacinalidad) throws ParseException {
		Nacionalidad entity = nacionalidadService.getByClave(claveNacinalidad);
		return entity != null ? gson.toJson(entity) : gson.toJson(new Nacionalidad());
	}
}
