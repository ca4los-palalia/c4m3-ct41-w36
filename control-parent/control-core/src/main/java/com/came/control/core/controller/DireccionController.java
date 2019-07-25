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
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;

@RestController
public class DireccionController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_DIRECCION_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Direccion itemProcess = gson.fromJson(jsonItem, Direccion.class);
		direccionService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_DIRECCION_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Direccion itemProcess = gson.fromJson(jsonItem, Direccion.class);
		direccionService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_DIRECCION_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Direccion itemProcess = direccionService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Direccion());
	}

	@PostMapping({ ConstMap.MAP_DIRECCION_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Direccion> list = direccionService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Direccion>());
	}

	@PostMapping({ ConstMap.MAP_DIRECCION_BY_CP })
	@Produces({ "application/json" })
	public String getByCp(@RequestBody String jsonItem) throws ParseException {
		List<Direccion> list = direccionService.getByCp(jsonItem);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Direccion>());
	}

	@PostMapping({ ConstMap.MAP_DIRECCION_BY_ESTADO })
	@Produces({ "application/json" })
	public String getByEstado(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = gson.fromJson(jsonItem, Estado.class);
		List<Direccion> list = direccionService.getByEstado(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Direccion>());
	}

}
