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
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;

@RestController
public class OficinaController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_OFICINA_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Oficina itemProcess = gson.fromJson(jsonItem, Oficina.class);
		oficinaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_OFICINA_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Oficina itemProcess = gson.fromJson(jsonItem, Oficina.class);
		oficinaService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_OFICINA_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Oficina itemProcess = oficinaService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Oficina());
	}

	@PostMapping({ ConstMap.MAP_OFICINA_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Oficina> list = oficinaService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Oficina>());
	}

	@PostMapping({ ConstMap.MAP_OFICINA_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Oficina> list = oficinaService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Oficina>());
	}

	@PostMapping({ ConstMap.MAP_OFICINA_BY_DIRECCION })
	@Produces({ "application/json" })
	public String getByDireccion(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Oficina itemProcess = oficinaService.getByDireccion(( (Direccion) map.get(ConstAtributos.DIRECCION)  ),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Oficina());
	}

}
