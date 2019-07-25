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
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Organizacion;

@RestController
public class EscolaridadController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Escolaridad itemProcess = gson.fromJson(jsonItem, Escolaridad.class);
		escolaridadService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Escolaridad entity = gson.fromJson(jsonItem, Escolaridad.class);
		escolaridadService.delete(entity);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Escolaridad entity = escolaridadService.getById(new Long(jsonItem));
		return entity != null ? gson.toJson(entity) : gson.toJson(new Escolaridad());
	}

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_BY_NOMBRE_AND_ORG })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String nombre = map.get(ConstAtributos.NOMBRE).toString();
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		Escolaridad itemReturn = escolaridadService.getByNombre(nombre, org);
		return itemReturn != null ? gson.toJson(itemReturn) : gson.toJson(new Escolaridad());
	}

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Escolaridad> list = escolaridadService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Escolaridad>());
	}

	@PostMapping({ ConstMap.MAP_ESCOLARIDAD_ALL_BY_ORG })
	@Produces({ "application/json" })
	public String getAllByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion org = organizacionService.getById(new Long(jsonItem));
		List<Escolaridad> list = escolaridadService.getAll(org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Escolaridad>());
	}
}
