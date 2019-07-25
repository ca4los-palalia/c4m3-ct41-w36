package com.came.control.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMap;
import com.came.control.core.ControllerMetaclas;
import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Organizacion;

@RestController
public class CalendariosController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_CALENDARIOS_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Calendarios itemProcess = gson.fromJson(jsonItem, Calendarios.class);
		calendariosService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_CALENDARIOS_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Calendarios itemProcess = gson.fromJson(jsonItem, Calendarios.class);
		calendariosService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_CALENDARIOS_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Calendarios itemProcess = calendariosService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Calendarios());
	}

	@PostMapping({ ConstMap.MAP_CALENDARIOS_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Calendarios> list = calendariosService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Calendarios>());
	}

	@PostMapping({ ConstMap.MAP_CALENDARIOS_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Calendarios> list = calendariosService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Calendarios>());
	}

}
