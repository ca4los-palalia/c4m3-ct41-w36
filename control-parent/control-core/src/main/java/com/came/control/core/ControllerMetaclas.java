package com.came.control.core;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.came.control.beans.funciones.CtrlUtilString;
import com.came.control.model.services.AsistenciaService;
import com.came.control.model.services.BancoService;
import com.came.control.model.services.CalendariosService;
import com.came.control.model.services.ConfiguracionService;
import com.came.control.model.services.DatosGeneralesService;
import com.came.control.model.services.DireccionService;
import com.came.control.model.services.EmergenciaService;
import com.came.control.model.services.EscolaridadService;
import com.came.control.model.services.EstadoCivilService;
import com.came.control.model.services.EstadoService;
import com.came.control.model.services.EstatusService;
import com.came.control.model.services.GeolocalizacionService;
import com.came.control.model.services.GrupoSanguineoService;
import com.came.control.model.services.HorarioService;
import com.came.control.model.services.IncidenciaService;
import com.came.control.model.services.MetodoPagoService;
import com.came.control.model.services.ModuloService;
import com.came.control.model.services.ModuloUsuarioService;
import com.came.control.model.services.MunicipioService;
import com.came.control.model.services.NacionalidadService;
import com.came.control.model.services.OficinaService;
import com.came.control.model.services.OrganizacionService;
import com.came.control.model.services.PaisService;
import com.came.control.model.services.PeriodoService;
import com.came.control.model.services.PersonaService;
import com.came.control.model.services.PoliticaHorarioService;
import com.came.control.model.services.PoliticasService;
import com.came.control.model.services.RolService;
import com.came.control.model.services.SexoService;
import com.came.control.model.services.TelefonoService;
import com.came.control.model.services.UsuarioIncidenciaService;
import com.came.control.model.services.UsuarioService;
import com.came.control.model.services.ZonaHorarioService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ControllerMetaclas {

	private static final Logger logger = Logger.getLogger(ControllerMetaclas.class);
	@Autowired
	protected AsistenciaService asistenciaService;
	@Autowired
	protected BancoService bancoService;
	@Autowired
	protected CalendariosService calendariosService;
	@Autowired
	protected ConfiguracionService configuracionService;
	@Autowired
	protected DatosGeneralesService datosGeneralesService;
	@Autowired
	protected DireccionService direccionService;
	@Autowired
	protected EmergenciaService emergenciaService;
	@Autowired
	protected EscolaridadService escolaridadService;
	@Autowired
	protected EstadoCivilService estadoCivilService;
	@Autowired
	protected EstadoService estadoService;
	@Autowired
	protected EstatusService estatusService;
	@Autowired
	protected GeolocalizacionService geolocalizacionService;
	@Autowired
	protected GrupoSanguineoService grupoSanguineoService;
	@Autowired
	protected HorarioService horarioService;
	@Autowired
	protected IncidenciaService incidenciasService;
	@Autowired
	protected MetodoPagoService metodoPagoService;
	@Autowired
	protected ModuloService moduloService;
	@Autowired
	protected ModuloUsuarioService moduloUsuarioService;
	@Autowired
	protected MunicipioService municipioService;
	@Autowired
	protected NacionalidadService nacionalidadService;
	@Autowired
	protected OficinaService oficinaService;
	@Autowired
	protected OrganizacionService organizacionService;
	@Autowired
	protected PaisService paisService;
	@Autowired
	protected PeriodoService periodoService;
	@Autowired
	protected PersonaService personaService;
	@Autowired
	protected PoliticaHorarioService politicaHorarioService;
	@Autowired
	protected PoliticasService politicasService;
	@Autowired
	protected RolService rolService;
	@Autowired
	protected SexoService sexoService;
	@Autowired
	protected TelefonoService telefonoService;
	@Autowired
	protected UsuarioService usuarioService;
	@Autowired
	protected UsuarioIncidenciaService usuarioIncidenciaService;
	@Autowired
	protected ZonaHorarioService zonaHorarioService;
	@Autowired
	protected CtrlUtilString ctrlUtilString;
	
	
	public Gson gson = new Gson();

	public Map<String, Object> jsonToHashMap(String json) {
		Map<String, Object> map = null;
		try {
			json = json.replaceAll("^\"|\"$|\\\\", "");
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
}
