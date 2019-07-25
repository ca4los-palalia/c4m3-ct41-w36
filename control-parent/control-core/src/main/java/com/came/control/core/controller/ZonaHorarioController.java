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
import com.came.control.model.domain.ZonaHorario;

@RestController
public class ZonaHorarioController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		ZonaHorario itemProcess = gson.fromJson(jsonItem, ZonaHorario.class);
		zonaHorarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		ZonaHorario itemProcess = gson.fromJson(jsonItem, ZonaHorario.class);
		zonaHorarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		ZonaHorario item = zonaHorarioService.getById(new Long(jsonItem));
		return item != null ? gson.toJson(item) : gson.toJson(new ZonaHorario());
	}
	
	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_BY_ZONA_HORARIO })
	@Produces({ "application/json" })
	public String getByZonaHorario(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Organizacion org = gson.fromJson(ctrlUtilString.decoderB64(map.get(ConstAtributos.ORGANIZACION).toString()), Organizacion.class);
		String zonaHoraria = map.get(ConstAtributos.ZONA_HORARIA).toString();
		
		ZonaHorario item = zonaHorarioService.getByZonaHorario(zonaHoraria, org);
		return item != null ? gson.toJson(item) : gson.toJson(new ZonaHorario());
	}

	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<ZonaHorario> list = zonaHorarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ZonaHorario>());
	}
	
	@PostMapping({ ConstMap.MAP_ZONA_HORARIO_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion org = gson.fromJson(jsonItem, Organizacion.class);
		List<ZonaHorario> list = zonaHorarioService.getByOrganizacion(org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ZonaHorario>());
	}

}
