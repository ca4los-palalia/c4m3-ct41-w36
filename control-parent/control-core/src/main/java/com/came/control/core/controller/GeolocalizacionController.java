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

@RestController
public class GeolocalizacionController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_GEOLOCALIZACION_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Geolocalizacion itemProcess = gson.fromJson(jsonItem, Geolocalizacion.class);
		geolocalizacionService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_GEOLOCALIZACION_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Geolocalizacion itemProcess = gson.fromJson(jsonItem, Geolocalizacion.class);
		geolocalizacionService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_GEOLOCALIZACION_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Geolocalizacion itemProcess = geolocalizacionService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Geolocalizacion());
	}

	@PostMapping({ ConstMap.MAP_GEOLOCALIZACION_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Geolocalizacion> list = geolocalizacionService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Geolocalizacion>());
	}

}
