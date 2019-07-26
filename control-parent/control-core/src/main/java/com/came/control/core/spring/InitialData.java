package com.came.control.core.spring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.core.AccesosTester;
import com.came.control.core.ImportFromExcelData;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.model.services.BancoService;
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
import com.came.control.model.services.PersonaService;
import com.came.control.model.services.RolService;
import com.came.control.model.services.SexoService;
import com.came.control.model.services.TelefonoService;
import com.came.control.model.services.UsuarioService;
import com.came.control.model.services.ZonaHorarioService;
import com.came.control.model.util.IteratorList;
import com.google.gson.Gson;

@Configuration
public class InitialData {

	private static final Logger logger = Logger.getLogger(InitialData.class);
	
	@Autowired
	private BancoService bancoService;
	@Autowired
	private IncidenciaService incidenciaService;
	@Autowired
	private ConfiguracionService configuracionService;
	@Autowired
	private EstadoCivilService estadoCivilService;
	@Autowired
	private GrupoSanguineoService grupoSanguineoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ModuloService moduloService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private MetodoPagoService metodoPagoService;
	@Autowired
	private ModuloUsuarioService moduloUsuarioService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private PaisService paisService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private TelefonoService telefonoService;
	@Autowired
	private DireccionService direccionService;
	@Autowired
	private EmergenciaService emergenciaService;
	@Autowired
	private SexoService sexoService;
	@Autowired
	private RolService rolService;
	@Autowired
	private EscolaridadService escolaridadService;
	@Autowired
	private EstatusService estatusService;
	@Autowired
	private DatosGeneralesService datosGeneralesService;
	@Autowired
	private OficinaService oficinaService;
	@Autowired
	private ZonaHorarioService zonaHorarioService;
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private NacionalidadService nacionalidadService;
	@Autowired
	private CtrlUtils ctrlUtils;
	@Autowired
	private IteratorList iteratorList;

	@Autowired
	private GeolocalizacionService geolocalizacionService;

	private List<Modulo> modulosCreados;
	private List<Incidencia> incidenciasNuevas;
	private List<Rol> rolList;
	private List<ZonaHorario> zonasHorarioNuevas;
	private List<Escolaridad> escolaridadesNueva;
	private List<MetodoPago> metodosPagosNueva;
	private List<Banco> bancosNueva;
	private AccesosTester builder;
	
	@Bean
	public String inicializarConfiguracion() {
		builder = new AccesosTester(geolocalizacionService, organizacionService, direccionService, datosGeneralesService, usuarioService, personaService, emergenciaService, telefonoService, moduloUsuarioService, zonaHorarioService, horarioService, ctrlUtils);
		crearDirectoriosDeTrabajo();
		crearCatalogos();
		crearModulos();
		construirPerfilRoot();
		construirPerfilTester();
		
		return "Configuracion inicial terminada";
	}

	private void builderConfiguracion(Organizacion org) {
		if(configuracionService.getByClaveAndOrg(ConstAtributos.CFG_COLOR_THEME, org) == null)
			configuracionService.save(iteratorList.crearColorThemeDefault(org));
		
		if(configuracionService.getByClaveAndOrg(ConstAtributos.CFG_ACCURACY, org) == null)
			configuracionService.save(iteratorList.crearAccuracyDefault(org));
		
		if(configuracionService.getByClaveAndOrg(ConstAtributos.CFG_MSG_INCIDENCIAS, org) == null)
			configuracionService.save(iteratorList.crearActivacionDescripcionIncidenciasDefault(org));
		
		if(configuracionService.getByClaveAndOrg(ConstAtributos.CFG_TIME_INCIDENCIAS, org) == null){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 20);
			cal.set(Calendar.MINUTE, 0);
			configuracionService.save(iteratorList.crearHoraDeCorteIncidenciasDefault(org, cal));
		}
			
		
	}

	private Oficina crearOficinaTemplate(Organizacion org) {
		Oficina of = new Oficina();
		of.setDescripcion("Oficina de prueba");
		
		Direccion dir = new Direccion();
		dir.setCalle("Direccion de oficina prueba1");
		dir.setColonia("Colonia de oficina prueba1");
		dir.setCp("23231");
		dir.setEstado(estadoService.getByNombre("Puebla"));
		dir.setNumeroExterior("2313");
		dir.setNumeroInterior("2");
		
		Telefono tel = new Telefono();
		tel.setCasa("00000000");
		tel.setOficina("23523522");
		telefonoService.save(tel);
		dir.setTelefono(tel);
		
		direccionService.save(dir);
		of.setDireccion(dir);

		of.setNombre("Oficina 1 tester");
		of.setGeolocalizacion(org.getGeolocalizacion());
		of.setOrganizacion(org);
		oficinaService.save(of);
		return of;
	}

	private void construirPerfilRoot() {
		Organizacion org = crearOrganizacionRoot();
		if(org != null) {
			builderConfiguracion(org);
			List<Rol> cloneList= clonRolSinId(rolList);
			for (Rol rol : cloneList) {
				rol.setOrganizacion(org);
				rolService.save(rol);
			}
			
			Oficina oficina = crearOficinaTemplate(org);
			Usuario usr = crearUsuarioRoot(org, oficina);
			if (modulosCreados != null) {
				
				List<Horario> horarioList = iteratorList.crearHorarioTemporal(usr, zonaHorarioService.getByZonaHorario(ConstAtributos.ZH_MEXICO_CITY, org));
				for (Horario horario2 : horarioList) {
					horarioService.save(horario2);
				}
				builder.asignarModulos2Usr(usr, modulosCreados);
			}
			updateOrgIncidencias(org);
			updateOrgZonasHorario(org);
			updateOrgEscolaridades(org);
			updateOrgBancos(org);
			updateOrgMetodoPago(org);
		}
	}
	
	private void construirPerfilTester() {
		
		Organizacion org = crearOrganizacionTester();
		if(org != null) {
			builderConfiguracion(org);
			List<Rol> cloneList= clonRolSinId(rolList);
			for (Rol rol : cloneList) {
				rol.setOrganizacion(org);
				rolService.save(rol);
			}
			
			Oficina oficina = crearOficinaTemplate(org);
			Usuario usr = crearUsuarioTester(org, oficina);
			
			if (usr != null) {
				List<Horario> horarioList = iteratorList.crearHorarioTemporal(usr, zonaHorarioService.getByZonaHorario(ConstAtributos.ZH_MEXICO_CITY, org));
				for (Horario horario2 : horarioList) {
					horarioService.save(horario2);
				}
				
				List<Modulo> modulosTest1 = new ArrayList<Modulo>();
				modulosTest1.add(moduloService.getByNombre(ConstAtributos.MODULO_CONFIGURACION));
				modulosTest1.add(moduloService.getByNombre(ConstAtributos.MODULO_CHECADOR));
				modulosTest1.add(moduloService.getByNombre(ConstAtributos.MODULO_TRABAJADORES));
				modulosTest1.add(moduloService.getByNombre(ConstAtributos.MODULO_CATALOGOS));
				builder.asignarModulos2Usr(usr, modulosTest1);
			}
			updateOrgIncidencias(org);
			updateOrgZonasHorario(org);
			updateOrgEscolaridades(org);
			updateOrgBancos(org);
			updateOrgMetodoPago(org);
		}
	}

	private List<Modulo> crearModulos() {
		List<Modulo> lista = moduloService.getAll();
		if (lista == null) {
			modulosCreados = new ArrayList<Modulo>();

			Modulo item = new Modulo();
			item.setDescripcion("registro de asistencia");
			item.setFechaActualizacion(ctrlUtils.getFechaActualConHora());
			item.setIcono("/images/menu/checador16.png");
			item.setNombre(ConstAtributos.MODULO_CHECADOR);
			item.setRuta("/modules/checador/checador.zul");
			moduloService.save(item);
			modulosCreados.add(item);
			
			item = new Modulo();
			item.setDescripcion("Configuración del sistema");
			item.setIcono("/images/menu/configure16.png");
			item.setNombre(ConstAtributos.MODULO_CONFIGURACION);
			//item.setRuta("/modules/controlPanel/configuracion.zul");
			
			
			List<Modulo> subModulos = new ArrayList<Modulo>();
			Modulo subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administrar oficinas de la organizacion");
			subModuloItem.setIcono("/images/menu/office16.png");
			subModuloItem.setNombre("Oficinas");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/oficinas.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administrar Organizaciones");
			subModuloItem.setIcono("/images/menu/organizacion16.png");
			subModuloItem.setNombre("Organizaciones");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/organizaciones.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administracion de asistencias de colaboradores");
			subModuloItem.setIcono("/images/menu/asistencia16.png");
			subModuloItem.setNombre("Asistencias");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/asistencias.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administracion del sistema CAME Control");
			subModuloItem.setIcono("/images/menu/cfgsystem16.png");
			subModuloItem.setNombre("Cfg Sistema");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/cfgSistema.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Configuracion del tema de la aplicacion");
			subModuloItem.setIcono("/images/menu/skincolor16.png");
			subModuloItem.setNombre("Skin color");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/cfgThemeColor.zul");
			subModulos.add(subModuloItem);
			
			item.setSubModulos(new Gson().toJson(subModulos));
			
			moduloService.save(item);
			modulosCreados.add(item);

			//============================================================
			item = new Modulo();
			item.setDescripcion("CRUD para usuarios y asignacion de permisos");
			item.setIcono("/images/menu/employer16.png");
			item.setNombre(ConstAtributos.MODULO_TRABAJADORES);
			item.setRuta("");
			
			subModulos = new ArrayList<Modulo>();
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administar altas, bajas y modificaciones de trabajadores");
			subModuloItem.setIcono("/images/menu/employer16.png");
			subModuloItem.setNombre("Administrar");
			subModuloItem.setRuta("/modules/controlPanel/usuariosAdmin.zul");
			subModulos.add(subModuloItem);
			item.setSubModulos(new Gson().toJson(subModulos));
			
			moduloService.save(item);
			modulosCreados.add(item);
			//============================================================
			
			
			
			//============================================================
			item = new Modulo();
			item.setDescripcion("Administracion de Catalogos");
			item.setFechaActualizacion(ctrlUtils.getFechaActualConHora());
			item.setIcono("/images/menu/catalog16.png");
			item.setNombre(ConstAtributos.MODULO_CATALOGOS);
			//item.setRuta("/modules/controlPanel/catalogos.zul");
			
			subModulos = new ArrayList<Modulo>();
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administracion de incidencias");
			subModuloItem.setIcono("/images/menu/incidencias16.png");
			subModuloItem.setNombre("Incidencias");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/incidencias.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administracion de zonas horario");
			subModuloItem.setIcono("/images/menu/timeZone16.png");
			subModuloItem.setNombre("Zona horarios");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/zonaHorario.zul");
			subModulos.add(subModuloItem);
			
			subModuloItem = new Modulo();
			subModuloItem.setDescripcion("Administrar Roles de usuarios");
			subModuloItem.setIcono("/images/menu/rol16.png");
			subModuloItem.setNombre("Rol de usuario");
			subModuloItem.setRuta("/modules/controlPanel/configuracion/roles.zul");
			subModulos.add(subModuloItem);
			
			
			item.setSubModulos(new Gson().toJson(subModulos));
			moduloService.save(item);
			modulosCreados.add(item);
			//============================================================
		}
		return lista;
	}

	private void crearDirectoriosDeTrabajo() {
		String home = crearCarpeta(ConstAtributos.PATH_HOME);
		home += System.getProperty("file.separator");
		crearCarpeta(home + ConstAtributos.PATH_CFG);
		crearCarpeta(home + ConstAtributos.PATH_LAYOUT);
		crearCarpeta(home + ConstAtributos.PATH_IMAGES_ESTADOS);
		crearCarpeta(home + ConstAtributos.PATH_IMAGES_ORG);
		crearCarpeta(home + ConstAtributos.PATH_IMAGES_PAISES);
		crearCarpeta(home + ConstAtributos.PATH_IMAGES_USUARIOS);
		crearCarpeta(home + ConstAtributos.PATH_IMAGES_TEMPLATES);

		String pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "companyProfile.png";
		if (!new File(pathOutput).exists())
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/companyProfile.png"),
					new File(pathOutput));

		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "companyProfileGif.gif";
		if (!new File(pathOutput).exists())
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/companyProfileGif.gif"),
					new File(pathOutput));
		
		
//		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "camectrl.png";
//		if (!new File(pathOutput).exists())
//			ctrlUtils.copyInputStreamToDiskOneFile(
//					this.getClass().getClassLoader().getResourceAsStream("/images/camectrl.png"),
//					new File(pathOutput));

		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "useradminTemplate.png";
		if (!new File(pathOutput).exists())
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/useradminTemplate.png"),
					new File(pathOutput));

		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "userTemplate.png";
		if (!new File(pathOutput).exists())
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/userTemplate.png"),
					new File(pathOutput));
		
		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "orgTemplate.png";
		if (!new File(pathOutput).exists())
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/orgTemplate.png"),
					new File(pathOutput));
		
		

		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_ESTADOS;
		if (new File(pathOutput).listFiles() == null || new File(pathOutput).listFiles().length == 0) {
			pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "estados.zip";
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/estados.zip"), new File(pathOutput));
			ctrlUtils.descomprimir(pathOutput, ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_ESTADOS);
		}

		pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_PAISES;
		if (new File(pathOutput).listFiles() == null || new File(pathOutput).listFiles().length == 0) {
			pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "paises.zip";
			ctrlUtils.copyInputStreamToDiskOneFile(
					this.getClass().getClassLoader().getResourceAsStream("/images/paises.zip"), new File(pathOutput));
			ctrlUtils.descomprimir(pathOutput, ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_PAISES);
		}

	}

	private String crearCarpeta(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		return file.getAbsolutePath();
	}

	private void crearCatalogos() {
		ImportFromExcelData importDate = null;

		if (paisService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(0);
		}

		if (estadoService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(1);
		}
			

		if (municipioService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(2);
		}
			
		if (estadoCivilService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(3);
		}
			
		if (grupoSanguineoService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(4);
		}
			
		if (sexoService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(5);
		}
			
		if (rolService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(6);
			
			rolList = rolService.getAll();
			for (Rol item : rolList) {
				rolService.delete(item);
				item.setIdRol(null);
			}
		}
			
		if (estatusService.getAll() == null) {
			importDate = precargarXcelScript();
			importDate.leerDatosDesdeExcel(7);
		}
			
		if (zonaHorarioService.getAll() == null) {
			importDate = precargarXcelScript();
			List<Object> listr = importDate.leerDatosDesdeExcel(8);
			if(listr != null && listr.size() > 0) {
				zonasHorarioNuevas = new ArrayList<ZonaHorario>();
				for (Object object : listr)
					zonasHorarioNuevas.add((ZonaHorario) object);
			}
		}
			
		if (incidenciaService.getAll() == null) {
			importDate = precargarXcelScript();
			
			List<Object> listr = importDate.leerDatosDesdeExcel(9);
			if(listr != null && listr.size() > 0) {
				incidenciasNuevas = new ArrayList<Incidencia>();
				for (Object object : listr)
					incidenciasNuevas.add((Incidencia) object);
			}
		}
		
		
		if(nacionalidadService.getAll() == null) {
			importDate = precargarXcelScript();
			List<Object> listr = importDate.leerDatosDesdeExcel(10);
			if(listr != null && listr.size() > 0) {
				for (Object object : listr)
					nacionalidadService.save((Nacionalidad) object);
			}
		}
		
		if(escolaridadService.getAll() == null) {
			importDate = precargarXcelScript();
			List<Object> listr = importDate.leerDatosDesdeExcel(11);
			if(listr != null && listr.size() > 0) {
				escolaridadesNueva = new ArrayList<Escolaridad>();
				for (Object object : listr)
					escolaridadesNueva.add((Escolaridad) object);
			}
		}
		
		if(metodoPagoService.getAll() == null) {
			importDate = precargarXcelScript();
			List<Object> listr = importDate.leerDatosDesdeExcel(12);
			if(listr != null && listr.size() > 0) {
				metodosPagosNueva = new ArrayList<MetodoPago>();
				for (Object object : listr)
					metodosPagosNueva.add((MetodoPago) object);
			}
		}
		
		if(bancoService.getAll() == null) {
			importDate = precargarXcelScript();
			List<Object> listr = importDate.leerDatosDesdeExcel(13);
			if(listr != null && listr.size() > 0) {
				bancosNueva = new ArrayList<Banco>();
				for (Object object : listr)
					bancosNueva.add((Banco) object);
			}
		}
		
	}

	private Usuario crearUsuarioRoot(Organizacion org, Oficina oficina) {
		Usuario usr = null;
		if (usuarioService.getAll() == null) {
			Map<String, Object> map = new HashMap<>();
			map.put(ConstAtributos.MAP_USR_ACTIVO, true);
			map.put(ConstAtributos.MAP_USR_NOMBRE, ctrlUtils.Encriptar("root"));
			map.put(ConstAtributos.MAP_USR_PASS, ctrlUtils.Encriptar("root"));
			map.put(ConstAtributos.MAP_USR_NIP, ctrlUtils.Encriptar("123456"));
			map.put(ConstAtributos.MAP_USR_IMAGEN,
					ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "useradminTemplate.png");
			map.put(ConstAtributos.MAP_USR_EMAIL, "carlos.palalia@gmail.com");
			map.put(ConstAtributos.MAP_USR_CURP, "ROOT201905CURPPR07");
			map.put(ConstAtributos.MAP_USR_NSS, "ROOT201900884NSS");
			map.put(ConstAtributos.MAP_USR_RFC, "ROOT201905RFC");
			map.put(ConstAtributos.MAP_USR_EDO_CIVIL, estadoCivilService.getByNombre(ConstMensajes.EDO_CIV_NO_APLICA));
			map.put(ConstAtributos.MAP_USR_GPO_SANGUINEO,
					grupoSanguineoService.getByNombre(ConstMensajes.GPO_SANG_O_POSITIVO));
			map.put(ConstAtributos.MAP_USR_DIR_CALLE, "Montes Urales");
			map.put(ConstAtributos.MAP_USR_DIR_COLONIA, "Lomas de Chapultepec");
			map.put(ConstAtributos.MAP_USR_DIR_CP, "11000");
			map.put(ConstAtributos.MAP_USR_DIR_ESTADO, estadoService.getByNombre(ConstAtributos.PUEBLA));
			map.put(ConstAtributos.MAP_USR_DIR_NUM_EXT, "0000");
			map.put(ConstAtributos.MAP_USR_DIR_NUM_INT, null);
			map.put(ConstAtributos.MAP_USR_TEL_CASA, "2487093");
			map.put(ConstAtributos.MAP_USR_TEL_MOVIL, "2987100505");
			map.put(ConstAtributos.MAP_USR_TEL_OFICINA, "89425840");
			map.put(ConstAtributos.MAP_USR_PERSONA_NOMBRE, "root");
			map.put(ConstAtributos.MAP_USR_PERSONA_APPATERNO, "root");
			map.put(ConstAtributos.MAP_USR_PERSONA_APMATERNO, "root");
			map.put(ConstAtributos.MAP_USR_SEXO, sexoService.getByNombre(ConstAtributos.HOMBRE));
			map.put(ConstAtributos.MAP_USR_ROL, rolService.getByNombre(ConstAtributos.ROL_SYSADMIN));
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_NOMBRE, "Skynet");
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_PATERNO, "Sintezoide");
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_MATERNO, "Sentinela");
			map.put(ConstAtributos.MAP_EMERGENCIA_TEL_CASA, "990823");
			map.put(ConstAtributos.MAP_EMERGENCIA_TEL_MOVIL, "924010343");
			map.put(ConstAtributos.MAP_ORGANIZACION, org);
			//map.put(ConstAtributos.MAP_USR_HORARIO, null);
			map.put(ConstAtributos.MAP_USR_OFICINA, oficina);
			
			usr = builder.crearUsuario(map);
		}
		return usr;
	}

	private Organizacion crearOrganizacionRoot() {
		Organizacion orgTemp = null;
		if (organizacionService.getAll() == null) {
			Map<String, Object> map = new HashMap<>();
			map.put(ConstAtributos.MAP_ORG_RAZON_SOCIAL, "CAME Accounting Solutions, S.A de C.V.");
			map.put(ConstAtributos.MAP_ORG_DESC, "Came software cholula");
			map.put(ConstAtributos.MAP_ORG_RFC, "PXOAIEE7688");
			map.put(ConstAtributos.MAP_ORG_LOGOTIPO, (ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "companyProfileGif.gif"));
			map.put(ConstAtributos.MAP_ORG_DIR_CALLE, "Doroteo Arango");
			map.put(ConstAtributos.MAP_ORG_DIR_COLONIA, "Ampliacion Momoxpan");
			map.put(ConstAtributos.MAP_ORG_DIR_CP, "72775");
			map.put(ConstAtributos.MAP_ORG_DIR_ESTADO, estadoService.getByNombre("Puebla"));
			map.put(ConstAtributos.MAP_ORG_DIR_NUM_INT, "1");
			map.put(ConstAtributos.MAP_ORG_DIR_NUM_EXT, "54");
			map.put(ConstAtributos.MAP_ORG_GEO_LATITUD, "19.078527688654603");
			map.put(ConstAtributos.MAP_ORG_GEO_LONGITUD, "-98.26586131633205");
			map.put(ConstAtributos.MAP_ORG_GEO_DESCRIPCION, null);

			orgTemp = builder.crearOrganizacion(map);
			//=====================================
		}
		return orgTemp;
	}
	
	private Organizacion crearOrganizacionTester() {
		Organizacion orgTemp = null;
		String rfc = "TEST1AXAXAA02";
		if(organizacionService.getByRfc(rfc) == null) {
			Map<String, Object> map = new HashMap<>();
			map.put(ConstAtributos.MAP_ORG_RAZON_SOCIAL, "Enterprise Tester");
			map.put(ConstAtributos.MAP_ORG_DESC, "Organizacion de Oaxaca");
			map.put(ConstAtributos.MAP_ORG_RFC, rfc);
			map.put(ConstAtributos.MAP_ORG_LOGOTIPO, (ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "orgTemplate.png"));
			map.put(ConstAtributos.MAP_ORG_DIR_CALLE, "Siempre viva");
			map.put(ConstAtributos.MAP_ORG_DIR_COLONIA, "Bugambilias");
			map.put(ConstAtributos.MAP_ORG_DIR_CP, "45645");
			map.put(ConstAtributos.MAP_ORG_DIR_ESTADO, estadoService.getByNombre("Oaxaca"));
			map.put(ConstAtributos.MAP_ORG_DIR_NUM_INT, "4");
			map.put(ConstAtributos.MAP_ORG_DIR_NUM_EXT, "423");
			map.put(ConstAtributos.MAP_ORG_GEO_LATITUD, "17.06094887");
			map.put(ConstAtributos.MAP_ORG_GEO_LONGITUD, "-96.72496319");
			map.put(ConstAtributos.MAP_ORG_GEO_DESCRIPCION, null);

			orgTemp = builder.crearOrganizacion(map);
		}
		return orgTemp;
	}

	private Usuario crearUsuarioTester(Organizacion org, Oficina oficina) {
		Usuario usr = null;
		String usrString = ctrlUtils.Encriptar("tester1");
		String psw = ctrlUtils.Encriptar("tester1");
		if (usuarioService.getAutenticacWithWeb(usrString, psw, org) == null) {
			Map<String, Object> map = new HashMap<>();
			map.put(ConstAtributos.MAP_USR_ACTIVO, true);
			map.put(ConstAtributos.MAP_USR_NOMBRE, usrString);
			map.put(ConstAtributos.MAP_USR_PASS, psw);
			map.put(ConstAtributos.MAP_USR_NIP, ctrlUtils.Encriptar("123456"));
			map.put(ConstAtributos.MAP_USR_IMAGEN,
					ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "useradminTemplate.png");
			map.put(ConstAtributos.MAP_USR_EMAIL, "1nn3rgy@gmail.com");
			map.put(ConstAtributos.MAP_USR_CURP, "TEST101905CURPPR07");
			map.put(ConstAtributos.MAP_USR_NSS, "TEST1019NSS");
			map.put(ConstAtributos.MAP_USR_RFC, "TEST101905RFC");
			map.put(ConstAtributos.MAP_USR_EDO_CIVIL, estadoCivilService.getByNombre(ConstMensajes.EDO_CIV_SOLTERO));
			map.put(ConstAtributos.MAP_USR_GPO_SANGUINEO,
					grupoSanguineoService.getByNombre(ConstMensajes.GPO_SANG_O_POSITIVO));
			map.put(ConstAtributos.MAP_USR_DIR_CALLE, "Manantiales");
			map.put(ConstAtributos.MAP_USR_DIR_COLONIA, "Mirador");
			map.put(ConstAtributos.MAP_USR_DIR_CP, "346346");
			map.put(ConstAtributos.MAP_USR_DIR_ESTADO, estadoService.getByNombre(ConstAtributos.PUEBLA));
			map.put(ConstAtributos.MAP_USR_DIR_NUM_EXT, "11111");
			map.put(ConstAtributos.MAP_USR_DIR_NUM_INT, null);
			map.put(ConstAtributos.MAP_USR_TEL_CASA, "263621");
			map.put(ConstAtributos.MAP_USR_TEL_MOVIL, "8246710357");
			map.put(ConstAtributos.MAP_USR_TEL_OFICINA, "246443634");
			map.put(ConstAtributos.MAP_USR_PERSONA_NOMBRE, "Danny");
			map.put(ConstAtributos.MAP_USR_PERSONA_APPATERNO, "Powers");
			map.put(ConstAtributos.MAP_USR_PERSONA_APMATERNO, "Test1");
			map.put(ConstAtributos.MAP_USR_SEXO, sexoService.getByNombre(ConstAtributos.HOMBRE));
			map.put(ConstAtributos.MAP_USR_ROL, rolService.getByNombre(ConstAtributos.ROL_GERENTE));
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_NOMBRE, "Skynet");
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_PATERNO, "Sintezoide");
			map.put(ConstAtributos.MAP_EMERGENCIA_PERSONA_AP_MATERNO, "Sentinela");
			map.put(ConstAtributos.MAP_EMERGENCIA_TEL_CASA, "990823");
			map.put(ConstAtributos.MAP_EMERGENCIA_TEL_MOVIL, "924010343");
			map.put(ConstAtributos.MAP_ORGANIZACION, org);
			//map.put(ConstAtributos.MAP_USR_HORARIO, null);
			map.put(ConstAtributos.MAP_USR_OFICINA, oficina);

			usr = builder.crearUsuario(map);
		}
		return usr;
	}
	
	private void updateOrgMetodoPago(Organizacion org) {
		if(metodosPagosNueva != null && metodosPagosNueva.size() > 0) {
			for (MetodoPago item : metodosPagosNueva) {
				MetodoPago itemClone = (MetodoPago) SerializationUtils.clone(item);
				itemClone.setOrganizacion(org);
				metodoPagoService.save(itemClone);
			}
		}
	}
	
	private void updateOrgBancos(Organizacion org) {
		if(bancosNueva != null && bancosNueva.size() > 0) {
			for (Banco item : bancosNueva) {
				Banco itemClone = (Banco) SerializationUtils.clone(item);
				itemClone.setOrganizacion(org);
				bancoService.save(itemClone);
			}
		}
	}
	
	private void updateOrgEscolaridades(Organizacion org) {
		if(escolaridadesNueva != null && escolaridadesNueva.size() > 0) {
			for (Escolaridad item : escolaridadesNueva) {
				Escolaridad itemClone = (Escolaridad) SerializationUtils.clone(item);
				itemClone.setOrganizacion(org);
				escolaridadService.save(itemClone);
			}
		}
	}
	
	private void updateOrgIncidencias(Organizacion org) {
		if(incidenciasNuevas != null && incidenciasNuevas.size() > 0) {
			for (Incidencia incidencia : incidenciasNuevas) {
				Incidencia in = (Incidencia) SerializationUtils.clone(incidencia);
				in.setOrganizacion(org);
				incidenciaService.save(in);
			}
		}
	}
	
	private void updateOrgZonasHorario(Organizacion org) {
		if(zonasHorarioNuevas != null && zonasHorarioNuevas.size() > 0) {
			for (ZonaHorario item : zonasHorarioNuevas) {
				ZonaHorario in = (ZonaHorario) SerializationUtils.clone(item);
				in.setOrganizacion(org);
				zonaHorarioService.save(in);
			}
		}
	}
	
	private ImportFromExcelData precargarXcelScript() {
		XSSFWorkbook workBook = null;
		ImportFromExcelData importDate = null;
		if(importDate == null) {
			try {
				workBook = new XSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("/Script.xlsx"));
				importDate = new ImportFromExcelData(workBook, paisService, estadoService, municipioService,
						estadoCivilService, grupoSanguineoService, sexoService, rolService, estatusService, zonaHorarioService, iteratorList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return importDate;
	}
	
	private List<Rol> clonRolSinId(List<Rol> origen){
		List<Rol> cloned = new ArrayList<Rol>();
		for (Rol rol : origen) {
			Rol newClone = new Rol();
			newClone.setDescripcion(rol.getDescripcion());
			newClone.setNombre(rol.getNombre());
			newClone.setOrganizacion(rol.getOrganizacion());
			cloned.add(newClone);
		}
		return cloned;
	}

//	
//	private AImage getAImage() {
//		AImage aimagen = null;
//		File file = null;
//		try {
//			file = new File(getClass().getClassLoader()
//					.getResource("layout/companyProfile.png").toURI());
//
//			aimagen = new AImage(file);
//		} catch (Exception e) {
//			System.out.println("El fichero " + file.getAbsolutePath()
//					+ " no se encuentra en el sistema");
//		}
//		return aimagen;
//	}

}