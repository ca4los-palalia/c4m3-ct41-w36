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

@RestController
public class PeriodoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_PERIODO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Periodo itemProcess = gson.fromJson(jsonItem, Periodo.class);
		periodoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_PERIODO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Periodo itemProcess = gson.fromJson(jsonItem, Periodo.class);
		periodoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_PERIODO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Periodo itemProcess = periodoService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Periodo());
	}

	@PostMapping({ ConstMap.MAP_PERIODO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Periodo> list = periodoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Periodo>());
	}

	@PostMapping({ ConstMap.MAP_PERIODO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Periodo itemProcess = periodoService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Periodo());
	}

}
