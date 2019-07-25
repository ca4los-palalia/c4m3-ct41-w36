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
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.PoliticaHorario;

@RestController
public class PoliticaHorarioController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		PoliticaHorario itemProcess = gson.fromJson(jsonItem, PoliticaHorario.class);
		politicaHorarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		PoliticaHorario itemProcess = gson.fromJson(jsonItem, PoliticaHorario.class);
		politicaHorarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		PoliticaHorario itemProcess = politicaHorarioService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new PoliticaHorario());
	}

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<PoliticaHorario> list = politicaHorarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<PoliticaHorario>());
	}

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		PoliticaHorario itemProcess = politicaHorarioService.getByNombre(map.get(ConstAtributos.NOMBRE).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new PoliticaHorario());
	}

	@PostMapping({ ConstMap.MAP_POLITICA_HORARIO_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<PoliticaHorario> list = politicaHorarioService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<PoliticaHorario>());
	}

}
