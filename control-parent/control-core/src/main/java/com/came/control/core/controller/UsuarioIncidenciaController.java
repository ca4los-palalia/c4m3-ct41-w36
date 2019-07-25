package com.came.control.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;

@RestController
public class UsuarioIncidenciaController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		UsuarioIncidencia itemProcess = gson.fromJson(jsonItem, UsuarioIncidencia.class);
		usuarioIncidenciaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		UsuarioIncidencia itemProcess = gson.fromJson(jsonItem, UsuarioIncidencia.class);
		usuarioIncidenciaService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		UsuarioIncidencia itemProcess = usuarioIncidenciaService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new UsuarioIncidencia());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<UsuarioIncidencia> list = usuarioIncidenciaService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<UsuarioIncidencia>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<UsuarioIncidencia> list = usuarioIncidenciaService.getByOrganizacion(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<UsuarioIncidencia>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_BY_USUARIO })
	@Produces({ "application/json" })
	public String getByUsuario(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		Usuario usr = usuarioService.getById(new Long(map.get(ConstAtributos.USUARIO).toString()), org);
		
		List<UsuarioIncidencia> list = usuarioIncidenciaService.getByUsuario(usr, org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<UsuarioIncidencia>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_BY_USUARIO_AND_FECHA })
	@Produces({ "application/json" })
	public String getByUsuarioAndFecha(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Date fecha= new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(map.get(ConstAtributos.FECHA).toString());
		
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		Usuario usr = usuarioService.getById(new Long(map.get(ConstAtributos.USUARIO).toString()), org);
		
		List<UsuarioIncidencia> list = usuarioIncidenciaService.getByUsuarioAndFecha(usr, org, fecha);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<UsuarioIncidencia>());
	}

	@PostMapping({ ConstMap.MAP_USUARIO_INCIDENCIA_BY_INCIDENCIA_AND_FECHA })
	@Produces({ "application/json" })
	public String getByIncidenciaAndFecha(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		Date fecha= new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(map.get(ConstAtributos.FECHA).toString());
		Organizacion org = (Organizacion) map.get(ConstAtributos.ORGANIZACION);
		Incidencia incidencia = (Incidencia) map.get(ConstAtributos.INCIDENCIA);
		
		List<UsuarioIncidencia> list = usuarioIncidenciaService.getByIncidenciaAndFecha(incidencia, org, fecha);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<UsuarioIncidencia>());
	}
}
