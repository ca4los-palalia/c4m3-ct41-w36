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
import com.came.control.model.domain.Pais;

@RestController
public class EstadoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ESTADO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = gson.fromJson(jsonItem, Estado.class);
		estadoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ESTADO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = gson.fromJson(jsonItem, Estado.class);
		estadoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ESTADO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = estadoService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estado());
	}

	@PostMapping({ ConstMap.MAP_ESTADO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Estado> list = estadoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Estado>());
	}

	@PostMapping({ ConstMap.MAP_ESTADO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = estadoService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estado());
	}
	
	@PostMapping({ ConstMap.MAP_ESTADO_BY_ABREVIATURA })
	@Produces({ "application/json" })
	public String getByAbreviatura(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = estadoService.getByAbreviatura(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estado());
	}
	
	@PostMapping({ ConstMap.MAP_ESTADO_BY_CAPITAL })
	@Produces({ "application/json" })
	public String getByCapital(@RequestBody String jsonItem) throws ParseException {
		Estado itemProcess = estadoService.getByCapital(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Estado());
	}

	@PostMapping({ ConstMap.MAP_ESTADO_BY_PAIS })
	@Produces({ "application/json" })
	public String getByPais(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = gson.fromJson(jsonItem, Pais.class);
		List<Estado> list = estadoService.getByPais(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Estado>());
	}

}
