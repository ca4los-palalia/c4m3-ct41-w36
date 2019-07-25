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
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;

@RestController
public class RolController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ROL_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Rol itemProcess = gson.fromJson(jsonItem, Rol.class);
		rolService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ROL_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Rol itemProcess = gson.fromJson(jsonItem, Rol.class);
		rolService.delete(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ROL_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Rol itemProcess = rolService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Rol());
	}

	@PostMapping({ ConstMap.MAP_ROL_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Rol> list = rolService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Rol>());
	}

	@PostMapping({ ConstMap.MAP_ROL_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Rol itemProcess = rolService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Rol());
	}
	
	@PostMapping({ ConstMap.MAP_ROL_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Rol> list = rolService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Rol>());
	}

}
