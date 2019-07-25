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
import com.came.control.model.domain.Estatus;

@RestController
public class EstatusController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ESTATUS_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Estatus itemProcess = gson.fromJson(jsonItem, Estatus.class);
		estatusService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ESTATUS_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Estatus itemProcess = gson.fromJson(jsonItem, Estatus.class);
		estatusService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ESTATUS_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Estatus itemProcess = estatusService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estatus());
	}

	@PostMapping({ ConstMap.MAP_ESTATUS_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Estatus> list = estatusService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Estatus>());
	}

	@PostMapping({ ConstMap.MAP_ESTATUS_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Estatus itemProcess = estatusService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estatus());
	}

}
