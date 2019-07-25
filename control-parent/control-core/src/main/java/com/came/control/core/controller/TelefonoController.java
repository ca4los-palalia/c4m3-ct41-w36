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
import com.came.control.model.domain.Telefono;

@RestController
public class TelefonoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_TELEFONO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Telefono itemProcess = gson.fromJson(jsonItem, Telefono.class);
		telefonoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_TELEFONO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Telefono itemProcess = gson.fromJson(jsonItem, Telefono.class);
		telefonoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_TELEFONO_BY_ID })
	@Produces({ "application/json" })
	public String getById(Long idEntity) throws ParseException {
		Telefono itemProcess = telefonoService.getById(new Long(String.valueOf(idEntity)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Telefono());
	}

	@PostMapping({ ConstMap.MAP_TELEFONO_ALL })
	@Produces({ "application/json" })
	public String getAll() throws ParseException {
		List<Telefono> list = telefonoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Telefono>());
	}

}
