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
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Organizacion;

@RestController
public class MetodoPagoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_METODO_PAGO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		MetodoPago itemProcess = gson.fromJson(jsonItem, MetodoPago.class);
		metodoPagoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_METODO_PAGO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		MetodoPago itemProcess = gson.fromJson(jsonItem, MetodoPago.class);
		metodoPagoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_METODO_PAGO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		MetodoPago itemProcess = metodoPagoService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new MetodoPago());
	}
	
	@PostMapping({ ConstMap.MAP_METODO_PAGO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String nombre = map.get(ConstAtributos.NOMBRE).toString();
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		MetodoPago itemProcess = metodoPagoService.getByNombre(nombre, org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new MetodoPago());
	}
	

	@PostMapping({ ConstMap.MAP_METODO_PAGO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<MetodoPago> list = metodoPagoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<MetodoPago>());
	}
	
	@PostMapping({ ConstMap.MAP_METODO_PAGO_ALL_BY_ORG })
	@Produces({ "application/json" })
	public String getAllByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion org = organizacionService.getById(new Long(jsonItem));
		List<MetodoPago> list = metodoPagoService.getAllByOrganizacion(org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<MetodoPago>());
	}

	
}
