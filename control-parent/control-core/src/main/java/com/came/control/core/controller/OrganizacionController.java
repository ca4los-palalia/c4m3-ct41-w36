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
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Organizacion;

@RestController
public class OrganizacionController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ORGANIZACION_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		organizacionService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ORGANIZACION_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		organizacionService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ORGANIZACION_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = organizacionService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Organizacion());
	}

	@PostMapping({ ConstMap.MAP_ORGANIZACION_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstMap.MAP_ORGANIZACION_BY_RFC })
	@Produces({ "application/json" })
	public String getByRfc(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = organizacionService.getByRfc(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Organizacion());
	}

	@PostMapping({ ConstMap.MAP_ORGANIZACION_BY_GEOLOCALIZACION })
	@Produces({ "application/json" })
	public String getByGeolocalizacion(@RequestBody String jsonItem) throws ParseException {
		Geolocalizacion finderObject = gson.fromJson(jsonItem, Geolocalizacion.class);
		Organizacion itemProcess = organizacionService.getByGeolocalizacion(finderObject);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Organizacion());
	}

}
