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
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;

@RestController
public class IncidenciasController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_INCIDENCIAS_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Incidencia itemProcess = gson.fromJson(jsonItem, Incidencia.class);
		incidenciasService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_INCIDENCIAS_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Incidencia itemProcess = gson.fromJson(jsonItem, Incidencia.class);
		incidenciasService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_INCIDENCIAS_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Incidencia itemProcess = incidenciasService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Incidencia());
	}

	@PostMapping({ ConstMap.MAP_INCIDENCIAS_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Incidencia> list = incidenciasService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Incidencia>());
	}

	@PostMapping({ ConstMap.MAP_INCIDENCIAS_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Incidencia> list = incidenciasService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Incidencia>());
	}

	
	@PostMapping({ ConstMap.MAP_INCIDENCIAS_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Incidencia itemProcess = incidenciasService.getByNombre(map.get(ConstAtributos.NOMBRE).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Incidencia());
	}
	
	@PostMapping({ ConstMap.MAP_INCIDENCIAS_BY_CLAVE })
	@Produces({ "application/json" })
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		Incidencia itemProcess = incidenciasService.getByClave(map.get(ConstAtributos.CLAVE).toString(), org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Incidencia());
	}
	
}
