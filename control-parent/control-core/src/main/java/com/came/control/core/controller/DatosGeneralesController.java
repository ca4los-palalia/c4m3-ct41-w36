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
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;

@RestController
public class DatosGeneralesController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		DatosGenerales itemProcess = gson.fromJson(jsonItem, DatosGenerales.class);
		datosGeneralesService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		DatosGenerales itemProcess = gson.fromJson(jsonItem, DatosGenerales.class);
		datosGeneralesService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		DatosGenerales itemProcess = datosGeneralesService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<DatosGenerales> list = datosGeneralesService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<DatosGenerales>());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<DatosGenerales> list = datosGeneralesService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<DatosGenerales>());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_PERSONA })
	@Produces({ "application/json" })
	public String getByPersona(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		DatosGenerales itemProcess = datosGeneralesService.getByPersona(( (Persona) map.get(ConstAtributos.PERSONA)),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_RFC })
	@Produces({ "application/json" })
	public String getByRfc(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		DatosGenerales itemProcess = datosGeneralesService.getByRfc(map.get(ConstAtributos.RFC).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_NSS })
	@Produces({ "application/json" })
	public String getByNss(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		DatosGenerales itemProcess = datosGeneralesService.getByNss(map.get(ConstAtributos.NSS).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_CURP })
	@Produces({ "application/json" })
	public String getByCurp(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		DatosGenerales itemProcess = datosGeneralesService.getByCurp(map.get(ConstAtributos.CURP).toString(),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

	@PostMapping({ ConstMap.MAP_DATOS_GENERALES_BY_ESTADO_CIVIL })
	@Produces({ "application/json" })
	public String getByEstadoCivil(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		DatosGenerales itemProcess = datosGeneralesService.getByEstadoCivil(
				((EstadoCivil) map.get(ConstAtributos.ESTADO_CIVIL)),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DatosGenerales());
	}

}
