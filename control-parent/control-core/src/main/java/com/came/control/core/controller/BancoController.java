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
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Organizacion;

@RestController
public class BancoController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_BANCO_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Banco itemProcess = gson.fromJson(jsonItem, Banco.class);
		bancoService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_BANCO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Banco itemProcess = gson.fromJson(jsonItem, Banco.class);
		bancoService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_BANCO_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Banco itemProcess = bancoService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Banco());
	}
	
	@PostMapping({ ConstMap.MAP_BANCO_BY_NOMBRE })
	@Produces({ "application/json" })
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String nombre = map.get(ConstAtributos.NOMBRE).toString();
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		Banco itemProcess = bancoService.getByNombre(nombre, org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Banco());
	}
	
	@PostMapping({ ConstMap.MAP_BANCO_BY_CLAVE })
	@Produces({ "application/json" })
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String clave = map.get(ConstAtributos.CLAVE).toString();
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		Banco itemProcess = bancoService.getByClave(clave, org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Banco());
	}
	
	@PostMapping({ ConstMap.MAP_BANCO_BY_RFC })
	@Produces({ "application/json" })
	public String getByRfc(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String rfc = map.get(ConstAtributos.RFC).toString();
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		Banco itemProcess = bancoService.getByRfc(rfc, org);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Banco());
	}
	

	@PostMapping({ ConstMap.MAP_BANCO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Banco> list = bancoService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Banco>());
	}
	
	@PostMapping({ ConstMap.MAP_BANCO_ALL_BY_ORG })
	@Produces({ "application/json" })
	public String getAllByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion org = organizacionService.getById(new Long(jsonItem));
		List<Banco> list = bancoService.getAllByOrganizacion(org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Banco>());
	}

	
}
