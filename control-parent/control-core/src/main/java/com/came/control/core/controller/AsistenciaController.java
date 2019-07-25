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
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

@RestController
public class AsistenciaController extends ControllerMetaclas {

	@PostMapping({ ConstMap.MAP_ASISTENCIA_SAVE })
	@Produces({ "application/json" })
	public String save(@RequestBody String jsonItem) throws ParseException {
		Asistencia itemProcess = gson.fromJson(jsonItem, Asistencia.class);
		asistenciaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Asistencia itemProcess = gson.fromJson(jsonItem, Asistencia.class);
		asistenciaService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_ID })
	@Produces({ "application/json" })
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Asistencia itemProcess = asistenciaService.getById(new Long(String.valueOf(map.get(ConstAtributos.ID_FINDER))),
				((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Asistencia());
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Asistencia> list = asistenciaService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Asistencia>());
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		String fechaString = map.get(ConstAtributos.FECHA).toString();
		Date fecha = fechaString.equals("*") ? null : new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(fechaString);
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		
		List<Asistencia> list = asistenciaService.getByOrganizacion(org, fecha);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Asistencia>());
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_FINALIZADOS })
	@Produces({ "application/json" })
	public String getByFinalizados(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		List<Asistencia> list = asistenciaService.getByFinalizados(Boolean.parseBoolean(map.get(ConstAtributos.FINALIZADO).toString()), ((Organizacion) map.get(ConstAtributos.ORGANIZACION)));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Asistencia>());
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_USR_AND_DATE })
	@Produces({ "application/json" })
	public String getByUsrAndDate(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Date fecha= new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(map.get(ConstAtributos.FECHA).toString());
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		Usuario usr = usuarioService.getById(new Long(map.get(ConstAtributos.USUARIO).toString()), org);
		
		Asistencia itemProcess = asistenciaService.getByUsrAndDate(usr, fecha, org);
		return itemProcess != null ? gson.toJson(itemProcess) : null;
	}
	
	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_USR_AND_DATE_WEEK })
	@Produces({ "application/json" })
	public String getByUsrAndDateWeek(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Date fechaInicio = new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(map.get(ConstAtributos.FECHA).toString());
		Date fechaFin = new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).parse(map.get(ConstAtributos.FECHA_FIN).toString());
		Organizacion org = organizacionService.getById(new Long(map.get(ConstAtributos.ORGANIZACION).toString()));
		Usuario usr = usuarioService.getById(new Long(map.get(ConstAtributos.USUARIO).toString()), org);
		
		List<Asistencia> list = asistenciaService.getByUsrAndDateWeek(usr, fechaInicio, fechaFin, org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Asistencia>());
	}

	@PostMapping({ ConstMap.MAP_ASISTENCIA_BY_FECHA })
	@Produces({ "application/json" })
	public String getByFecha(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";

		Date fecha= map.get(ConstAtributos.FECHA) == null ? null : (Date) map.get(ConstAtributos.FECHA);
		Organizacion org = map.get(ConstAtributos.ORGANIZACION) == null ? null : (Organizacion) map.get(ConstAtributos.ORGANIZACION);
		
		List<Asistencia> list = asistenciaService.getByFecha(fecha, org);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Asistencia>());
	}
}
