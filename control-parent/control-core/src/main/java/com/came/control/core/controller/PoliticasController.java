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
import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Politicas;

@RestController
public class PoliticasController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_POLITICAS_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Politicas itemProcess = gson.fromJson(jsonItem, Politicas.class);
		politicasService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_POLITICAS_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Politicas itemProcess = gson.fromJson(jsonItem, Politicas.class);
		politicasService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_POLITICAS_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Politicas itemProcess = politicasService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Politicas());
	}

	@PostMapping({ ConstMap.MAP_POLITICAS_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Politicas> list = politicasService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Politicas>());
	}

	@PostMapping({ ConstMap.MAP_POLITICAS_BY_PERIODO })
	@Produces({ "application/json" })
	public String getByPeriodo(@RequestBody String jsonItem) throws ParseException {
		Periodo objectFinder = gson.fromJson(jsonItem, Periodo.class);
		List<Politicas> list = politicasService.getByPeriodo(objectFinder);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Politicas>());
	}

}
