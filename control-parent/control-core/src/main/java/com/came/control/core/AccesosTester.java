package com.came.control.core;

import java.util.List;
import java.util.Map;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.model.services.DatosGeneralesService;
import com.came.control.model.services.DireccionService;
import com.came.control.model.services.EmergenciaService;
import com.came.control.model.services.GeolocalizacionService;
import com.came.control.model.services.HorarioService;
import com.came.control.model.services.ModuloUsuarioService;
import com.came.control.model.services.OrganizacionService;
import com.came.control.model.services.PersonaService;
import com.came.control.model.services.TelefonoService;
import com.came.control.model.services.UsuarioService;
import com.came.control.model.services.ZonaHorarioService;

public class AccesosTester {
	
	private GeolocalizacionService geolocalizacionService;
	private OrganizacionService organizacionService;
	private DireccionService direccionService;	
	private DatosGeneralesService datosGeneralesService;
	private UsuarioService usuarioService;
	private PersonaService personaService;
	private EmergenciaService emergenciaService;
	private TelefonoService telefonoService;
	private ModuloUsuarioService moduloUsuarioService;
	private HorarioService horarioService;
	private ZonaHorarioService zonaHorarioService;
	private CtrlUtils ctrlUtils;
	
	
	

	public AccesosTester(GeolocalizacionService geolocalizacionService, OrganizacionService organizacionService, DireccionService direccionService, DatosGeneralesService datosGeneralesService, UsuarioService usuarioService, PersonaService personaService, EmergenciaService emergenciaService, TelefonoService telefonoService, ModuloUsuarioService moduloUsuarioService, ZonaHorarioService zonaHorarioService, HorarioService horarioService, CtrlUtils ctrlUtils) {
		this.geolocalizacionService = geolocalizacionService;
		this.organizacionService = organizacionService;
		this.direccionService = direccionService;
		this.datosGeneralesService = datosGeneralesService;
		this.usuarioService = usuarioService;
		this.personaService = personaService;
		this.emergenciaService = emergenciaService;
		this.telefonoService = telefonoService;
		this.moduloUsuarioService = moduloUsuarioService;
		this.zonaHorarioService = zonaHorarioService;
		this.horarioService = horarioService;
		this.ctrlUtils = ctrlUtils;
	}

	public Organizacion crearOrganizacion(Map<String, Object> map) {
		Organizacion orgTemp = null;

		orgTemp = new Organizacion();
		String valor = (map.get(ConstAtributos.MAP_ORG_DESC) == null ? null : map.get(ConstAtributos.MAP_ORG_DESC).toString());
		orgTemp.setDescripcion(valor);

		Direccion dir = new Direccion();
		valor = (map.get(ConstAtributos.MAP_ORG_DIR_CALLE) == null ? null : map.get(ConstAtributos.MAP_ORG_DIR_CALLE).toString());
		dir.setCalle(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_DIR_COLONIA) == null ? null : map.get(ConstAtributos.MAP_ORG_DIR_COLONIA).toString());
		dir.setColonia(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_DIR_CP) == null ? null : map.get(ConstAtributos.MAP_ORG_DIR_CP).toString());
		dir.setCp(valor);
		
		Estado estado = (map.get(ConstAtributos.MAP_ORG_DIR_ESTADO) == null ? null : (Estado) map.get(ConstAtributos.MAP_ORG_DIR_ESTADO));
		dir.setEstado(estado);
		valor = (map.get(ConstAtributos.MAP_ORG_DIR_NUM_INT) == null ? null : map.get(ConstAtributos.MAP_ORG_DIR_NUM_INT).toString());
		dir.setNumeroInterior(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_DIR_NUM_EXT) == null ? null : map.get(ConstAtributos.MAP_ORG_DIR_NUM_EXT).toString());
		dir.setNumeroExterior(valor);

		direccionService.save(dir);
		orgTemp.setDireccion(dir);

		Geolocalizacion geo = new Geolocalizacion();
		valor = (map.get(ConstAtributos.MAP_ORG_GEO_LATITUD) == null ? null : map.get(ConstAtributos.MAP_ORG_GEO_LATITUD).toString());
		geo.setLatitud(valor);
		
		valor = (map.get(ConstAtributos.MAP_ORG_GEO_LONGITUD) == null ? null : map.get(ConstAtributos.MAP_ORG_GEO_LONGITUD).toString());
		geo.setLongitud(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_GEO_DESCRIPCION) == null ? null : map.get(ConstAtributos.MAP_ORG_GEO_DESCRIPCION).toString());
		geo.setDescripcion(valor);
		
		geolocalizacionService.save(geo);
		orgTemp.setGeolocalizacion(geo);

		valor = (map.get(ConstAtributos.MAP_ORG_RAZON_SOCIAL) == null ? null : map.get(ConstAtributos.MAP_ORG_RAZON_SOCIAL).toString());
		orgTemp.setRazonSocial(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_RFC) == null ? null : map.get(ConstAtributos.MAP_ORG_RFC).toString());
		orgTemp.setRfc(valor);
		valor = (map.get(ConstAtributos.MAP_ORG_LOGOTIPO) == null ? null : map.get(ConstAtributos.MAP_ORG_LOGOTIPO).toString());
		orgTemp.setLogotipo(valor);

		organizacionService.save(orgTemp);
		return orgTemp;
	}


	public Usuario crearUsuario(Map<String, Object> map) {
		String valor = (map.get(ConstAtributos.MAP_USR_ACTIVO) == null ? "false" : map.get(ConstAtributos.MAP_USR_ACTIVO).toString());

		Usuario usrNuevo = new Usuario();
		usrNuevo.setActivo(new Boolean(valor));
		valor = (map.get(ConstAtributos.MAP_USR_NOMBRE) == null ? null : map.get(ConstAtributos.MAP_USR_NOMBRE).toString());
		usrNuevo.setNombreUsuario(valor);
		valor = (map.get(ConstAtributos.MAP_USR_PASS) == null ? null : map.get(ConstAtributos.MAP_USR_PASS).toString());
		usrNuevo.setContrasenia(valor);
		valor = (map.get(ConstAtributos.MAP_USR_NIP) == null ? null : map.get(ConstAtributos.MAP_USR_NIP).toString());
		usrNuevo.setNip(valor);
		valor = (map.get(ConstAtributos.MAP_USR_IMAGEN) == null ? null : map.get(ConstAtributos.MAP_USR_IMAGEN).toString());
		usrNuevo.setImagen(valor);

		Estatus estatus = (map.get(ConstAtributos.MAP_USR_ESTATUS) == null ? null
				: (Estatus) map.get(ConstAtributos.MAP_USR_ESTATUS));
		usrNuevo.setEstatus(estatus);

		DatosGenerales datosGenerales = new DatosGenerales();
		valor = (map.get(ConstAtributos.MAP_USR_EMAIL) == null ? null : map.get(ConstAtributos.MAP_USR_EMAIL).toString());
		datosGenerales.setCorreo(valor);
		valor = (map.get(ConstAtributos.MAP_USR_CURP) == null ? null : map.get(ConstAtributos.MAP_USR_CURP).toString());
		datosGenerales.setCurp(valor);
		valor = (map.get(ConstAtributos.MAP_USR_NSS) == null ? null : map.get(ConstAtributos.MAP_USR_NSS).toString());
		datosGenerales.setNss(valor);
		valor = (map.get(ConstAtributos.MAP_USR_RFC) == null ? null : map.get(ConstAtributos.MAP_USR_RFC).toString());
		datosGenerales.setRfc(valor);

		EstadoCivil edoCivil = (map.get(ConstAtributos.MAP_USR_EDO_CIVIL) == null ? null : (EstadoCivil) map.get(ConstAtributos.MAP_USR_EDO_CIVIL));
		datosGenerales.setEstadoCivil(edoCivil);
		GrupoSanguineo gSang = (map.get(ConstAtributos.MAP_USR_GPO_SANGUINEO) == null ? null : (GrupoSanguineo) map.get(ConstAtributos.MAP_USR_GPO_SANGUINEO));
		datosGenerales.setGrupoSanguineo(gSang);

		Direccion dir = new Direccion();
		valor = (map.get(ConstAtributos.MAP_USR_DIR_CALLE) == null ? null : map.get(ConstAtributos.MAP_USR_DIR_CALLE).toString());
		dir.setCalle(valor);
		valor = (map.get(ConstAtributos.MAP_USR_DIR_COLONIA) == null ? null : map.get(ConstAtributos.MAP_USR_DIR_COLONIA).toString());
		dir.setColonia(valor);
		valor = (map.get(ConstAtributos.MAP_USR_DIR_CP) == null ? null : map.get(ConstAtributos.MAP_USR_DIR_CP).toString());
		dir.setCp(valor);
		Estado edo = (map.get(ConstAtributos.MAP_USR_DIR_ESTADO) == null ? null : (Estado) map.get(ConstAtributos.MAP_USR_DIR_ESTADO));
		dir.setEstado(edo);
		valor = (map.get(ConstAtributos.MAP_USR_DIR_NUM_EXT) == null ? null : map.get(ConstAtributos.MAP_USR_DIR_NUM_EXT).toString());
		dir.setNumeroExterior(valor);
		valor = (map.get(ConstAtributos.MAP_USR_DIR_NUM_INT) == null ? null : map.get(ConstAtributos.MAP_USR_DIR_NUM_INT).toString());
		dir.setNumeroInterior(valor);

		Telefono tel = new Telefono();
		valor = (map.get(ConstAtributos.MAP_USR_TEL_CASA) == null ? null : map.get(ConstAtributos.MAP_USR_TEL_CASA).toString());
		tel.setCasa(valor);
		valor = (map.get(ConstAtributos.MAP_USR_TEL_MOVIL) == null ? null : map.get(ConstAtributos.MAP_USR_TEL_MOVIL).toString());
		tel.setMovil(valor);
		valor = (map.get(ConstAtributos.MAP_USR_TEL_OFICINA) == null ? null : map.get(ConstAtributos.MAP_USR_TEL_OFICINA).toString());
		tel.setOficina(valor);

		telefonoService.save(tel);
		dir.setTelefono(tel);

		direccionService.save(dir);
		datosGenerales.setDireccion(dir);

		Emergencia emrg = new Emergencia();

		Persona personaEmergencia = new Persona();
		valor = (map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_NOMBRE) == null ? null : map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_NOMBRE).toString());
		personaEmergencia.setNombre(valor);
		valor = (map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_PATERNO) == null ? null : map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_PATERNO).toString());
		personaEmergencia.setApPaterno(valor);
		valor = (map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_MATERNO) == null ? null : map.get(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_MATERNO).toString());
		personaEmergencia.setApMaterno(valor);

		personaService.save(personaEmergencia);
		emrg.setPersona(personaEmergencia);

		Telefono telEmergencia = new Telefono();
		valor = (map.get(ConstAtributos.MAP_EMERGENCIA_TEL_CASA) == null ? null : map.get(ConstAtributos.MAP_EMERGENCIA_TEL_CASA).toString());
		telEmergencia.setCasa(valor);
		valor = (map.get(ConstAtributos.MAP_EMERGENCIA_TEL_MOVIL) == null ? null : map.get(ConstAtributos.MAP_EMERGENCIA_TEL_MOVIL).toString());
		telEmergencia.setMovil(valor);

		telefonoService.save(telEmergencia);
		emrg.setTelefono(telEmergencia);

		emergenciaService.save(emrg);
		datosGenerales.setEmergencia(emrg);

		Organizacion org = (map.get(ConstAtributos.MAP_ORGANIZACION) == null ? null : (Organizacion) map.get(ConstAtributos.MAP_ORGANIZACION));
		datosGenerales.setOrganizacion(org);

		Persona personaUsuario = new Persona();
		valor = (map.get(ConstAtributos.MAP_USR_PERSONA_NOMBRE) == null ? null : map.get(ConstAtributos.MAP_USR_PERSONA_NOMBRE).toString());
		personaUsuario.setNombre(valor);
		valor = (map.get(ConstAtributos.MAP_USR_PERSONA_APPATERNO) == null ? null : map.get(ConstAtributos.MAP_USR_PERSONA_APPATERNO).toString());
		personaUsuario.setApPaterno(valor);
		valor = (map.get(ConstAtributos.MAP_USR_PERSONA_APMATERNO) == null ? null : map.get(ConstAtributos.MAP_USR_PERSONA_APMATERNO).toString());
		personaUsuario.setApMaterno(valor);

		personaService.save(personaUsuario);
		datosGenerales.setPersona(personaUsuario);

		Usuario responsable = (map.get(ConstAtributos.MAP_USR_RESPONSABLE) == null ? null : (Usuario) map.get(ConstAtributos.MAP_USR_RESPONSABLE));
		datosGenerales.setResponsable(responsable);

		Sexo sex = (map.get(ConstAtributos.MAP_USR_SEXO) == null ? null : (Sexo) map.get(ConstAtributos.MAP_USR_SEXO));
		datosGenerales.setSexo(sex);

		datosGeneralesService.save(datosGenerales);
		usrNuevo.setDatosGenerales(datosGenerales);
		
		Oficina oficina = (map.get(ConstAtributos.MAP_USR_OFICINA) == null ? null : (Oficina) map.get(ConstAtributos.MAP_USR_OFICINA));
		usrNuevo.setOficina(oficina);
		usrNuevo.setOrganizacion(org);
		Rol itemRol = (map.get(ConstAtributos.MAP_USR_ROL) == null ? null : (Rol) map.get(ConstAtributos.MAP_USR_ROL));
		usrNuevo.setRol(itemRol);

		usuarioService.save(usrNuevo);
		return usrNuevo;

	}

	public void asignarModulos2Usr(Usuario usr, List<Modulo> lista) {
		for (Modulo item : lista) {
			ModuloUsuario mu = new ModuloUsuario();
			mu.setActivar(true);
			mu.setFechaActualizacion(ctrlUtils.getFechaActualConHora());
			mu.setModulo(item);
			mu.setUsuario(usr);
			moduloUsuarioService.save(mu);
		}
	}
	
//	public List<Horario> crearHorarioTemporal(Usuario usr, ZonaHorario zh) {
//		List<Horario> horarioTemplate = new ArrayList<Horario>();
//		//ZonaHorario zh = zonaHorarioService.getByZonaHorario(ConstAtributos.ZH_MEXICO_CITY);
//		
//		Horario horario = new Horario();
//		horario.setDescanso(false);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.LUNES));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(false);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.MARTES));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(false);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.MIERCOLES));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(false);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.JUEVES));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(false);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.VIERNES));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(true);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.SABADO));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		
//		horario = new Horario();
//		horario.setDescanso(true);
//		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.DOMINGO));
//		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
//		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
//		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
//		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
//		horario.setZonaHorario(zh);
//		//horario.setUsuario(usr);
//		horarioTemplate.add(horario);
//		return horarioTemplate;
//	}
}
