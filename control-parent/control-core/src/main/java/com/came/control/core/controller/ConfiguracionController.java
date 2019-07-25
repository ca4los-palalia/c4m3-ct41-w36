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
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;

@RestController
public class ConfiguracionController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_CONFIGURACION_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Configuracion itemProcess = gson.fromJson(jsonItem, Configuracion.class);
		configuracionService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_CONFIGURACION_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Configuracion itemProcess = gson.fromJson(jsonItem, Configuracion.class);
		configuracionService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_CONFIGURACION_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Organizacion org = organizacionService.getById(new Long(String.valueOf(map.get(ConstAtributos.ORGANIZACION))));
		Configuracion itemProcess = configuracionService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))), org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Configuracion());
	}

	@PostMapping({ ConstMap.MAP_CONFIGURACION_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Configuracion> list = configuracionService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Configuracion>());
	}

	@PostMapping({ ConstMap.MAP_CONFIGURACION_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		Organizacion org = organizacionService.getById(new Long(String.valueOf(map.get(ConstAtributos.ORGANIZACION))));
		
		List<Configuracion> list = configuracionService.getByOrganizacion(org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Configuracion>());
	}

	@PostMapping({ ConstMap.MAP_CONFIGURACION_BY_CLAVE_AND_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByClaveAndOrg(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Organizacion org = organizacionService.getById(new Long(String.valueOf(map.get(ConstAtributos.ORGANIZACION))));
		Configuracion itemProcess = configuracionService.getByClaveAndOrg(String.valueOf(map.get(ConstAtributos.CLAVE)), org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Configuracion());
	}
}
