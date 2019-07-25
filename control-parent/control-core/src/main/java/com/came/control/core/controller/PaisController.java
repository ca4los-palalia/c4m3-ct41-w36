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
import com.came.control.model.domain.Pais;

@RestController
public class PaisController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_PAIS_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = gson.fromJson(jsonItem, Pais.class);
		paisService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_PAIS_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = gson.fromJson(jsonItem, Pais.class);
		paisService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_PAIS_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = paisService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Pais());
	}

	@PostMapping({ ConstMap.MAP_PAIS_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Pais> list = paisService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Pais>());
	}

	@PostMapping({ ConstMap.MAP_PAIS_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = paisService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Pais());
	}
	
	@PostMapping({ ConstMap.MAP_PAIS_BY_CLAVE })
	@Produces({ "application/json" })
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
		Pais itemProcess = paisService.getByClave(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Pais());
	}

}
