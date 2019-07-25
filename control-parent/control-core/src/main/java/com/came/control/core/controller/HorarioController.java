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
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;

@RestController
public class HorarioController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_HORARIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Horario itemProcess = gson.fromJson(jsonItem, Horario.class);
		horarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_HORARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Horario itemProcess = gson.fromJson(jsonItem, Horario.class);
		horarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_HORARIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Horario itemProcess = horarioService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Horario());
	}

	@PostMapping({ ConstMap.MAP_HORARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Horario> list = horarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Horario>());
	}

	@PostMapping({ ConstMap.MAP_HORARIO_BY_USUARIO })
	@Produces({ "application/json" })
	public String getByUsuario(@RequestBody String jsonItem) throws ParseException {
		Usuario usuario = gson.fromJson(jsonItem, Usuario.class);
		List<Horario> list = horarioService.getByUsuario(usuario);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Horario>());
	}

	@PostMapping({ ConstMap.MAP_HORARIO_WITH_DESCANSO })
	@Produces({ "application/json" })
	public String getWithDescanso(@RequestBody String jsonItem) throws ParseException {
		List<Horario> list = horarioService.getWithDescanso(Boolean.getBoolean(jsonItem));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Horario>());
	}

	@PostMapping({ ConstMap.MAP_HORARIO_BY_ZONA_HORARIO })
	@Produces({ "application/json" })
	public String getByZonaHorario(@RequestBody String jsonItem) throws ParseException {
		ZonaHorario itemProcess = gson.fromJson(jsonItem, ZonaHorario.class);
		List<Horario> list = horarioService.getByZonaHorario(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Horario>());
	}

}
