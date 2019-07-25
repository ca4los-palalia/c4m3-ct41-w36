package com.came.control.web;

import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.management.MalformedObjectNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.came.control.beans.CfgCore;
import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.funciones.CtrlUtilString;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.beans.funciones.Funciones;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Municipio;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Pais;
import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Persona;
import com.came.control.model.domain.PoliticaHorario;
import com.came.control.model.domain.Politicas;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;
import com.came.control.model.domain.ZonaHorario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class RestMetaclas {

	public CfgCore cfg;
	public Gson gson;

	@Autowired
	private Funciones funciones;
	@Autowired
	public CtrlUtilString ctrlUtilString;
	@Autowired
	public CtrlUtils ctrlUtils;

	@SuppressWarnings("static-access")
	@PostConstruct
	public void init() {
		gson = new Gson();

		try {
			cfg = funciones.getInstanciaDelServidor();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String procesarRestFULL(String url, String parametros) {
		String response = "";
		try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, parametros, String.class);
		} catch (Exception e) {
			response = ConstAtributos.ERROR_EXCEPTION + ": " + e.getMessage() + " (Intente mas tarde)\nURL:" + url
					+ "\nParametros: " + parametros + "\n" + ctrlUtilString.exceptionToString(e);
		}
		return response;
	}

	
	public Banco createResponseToBanco(String response) {
		Banco responseObject = gson.fromJson(response, Banco.class);
		return responseObject == null || responseObject.getIdBanco() == null ? null : responseObject;
	}

	public List<Banco> createResponseToListBanco(String response) {
		TypeToken<List<Banco>> token = new TypeToken<List<Banco>>() {};
		List<Banco> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	public MetodoPago createResponseToMetodoPago(String response) {
		MetodoPago responseObject = gson.fromJson(response, MetodoPago.class);
		return responseObject == null || responseObject.getIdMetodoPago() == null ? null : responseObject;
	}

	public List<MetodoPago> createResponseToListMetodoPago(String response) {
		TypeToken<List<MetodoPago>> token = new TypeToken<List<MetodoPago>>() {};
		List<MetodoPago> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	public Asistencia createResponseToAsistencia(String response) {
		Asistencia responseObject = gson.fromJson(response, Asistencia.class);
		return responseObject == null || responseObject.getIdAsistencia() == null ? null : responseObject;
	}

	public List<Asistencia> createResponseToListAsistencia(String response) {
		TypeToken<List<Asistencia>> token = new TypeToken<List<Asistencia>>() {};
		List<Asistencia> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	public Nacionalidad createResponseToNacionalidad(String response) {
		Nacionalidad responseObject = gson.fromJson(response, Nacionalidad.class);
		return responseObject == null || responseObject.getIdNacionalidad() == null ? null : responseObject;
	}

	public List<Nacionalidad> createResponseToListNacionalidad(String response) {
		TypeToken<List<Nacionalidad>> token = new TypeToken<List<Nacionalidad>>() {};
		List<Nacionalidad> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	
	public Escolaridad createResponseToEscolaridad(String response) {
		Escolaridad responseObject = gson.fromJson(response, Escolaridad.class);
		return responseObject == null || responseObject.getIdEscolaridad() == null ? null : responseObject;
	}

	public List<Escolaridad> createResponseToListEscolaridad(String response) {
		TypeToken<List<Escolaridad>> token = new TypeToken<List<Escolaridad>>() {};
		List<Escolaridad> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	
	
	
	public Configuracion createResponseToConfiguracion(String response) {
		Configuracion responseObject = gson.fromJson(response, Configuracion.class);
		return responseObject == null || responseObject.getIdConfiguracion() == null ? null : responseObject;
	}

	public List<Configuracion> createResponseToListConfiguracion(String response) {
		TypeToken<List<Configuracion>> token = new TypeToken<List<Configuracion>>() {};
		List<Configuracion> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}
	
	

	public CtrlRestService createResponseToCalendarios(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Calendarios responseObject = gson.fromJson(response, Calendarios.class);
		itemReturn.setSingle((responseObject.getIdCalendarios() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListCalendarios(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Calendarios>> token = new TypeToken<List<Calendarios>>() {
		};
		List<Calendarios> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToDatosGenerales(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		DatosGenerales responseObject = gson.fromJson(response, DatosGenerales.class);
		itemReturn.setSingle((responseObject.getIdDatosGenerales() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListDatosGenerales(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<DatosGenerales>> token = new TypeToken<List<DatosGenerales>>() {
		};
		List<DatosGenerales> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToDireccion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Direccion responseObject = gson.fromJson(response, Direccion.class);
		itemReturn.setSingle((responseObject.getIdDireccion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListDireccion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Direccion>> token = new TypeToken<List<Direccion>>() {
		};
		List<Direccion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToEmergencia(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Emergencia responseObject = gson.fromJson(response, Emergencia.class);
		itemReturn.setSingle((responseObject.getIdEmergencia() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListEmergencia(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Emergencia>> token = new TypeToken<List<Emergencia>>() {
		};
		List<Emergencia> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToEstadoCivil(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		EstadoCivil responseObject = gson.fromJson(response, EstadoCivil.class);
		itemReturn.setSingle((responseObject.getIdEstadoCivil() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListEstadoCivil(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<EstadoCivil>> token = new TypeToken<List<EstadoCivil>>() {
		};
		List<EstadoCivil> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setLista(responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToEstado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Estado responseObject = gson.fromJson(response, Estado.class);
		itemReturn.setSingle((responseObject.getIdEstado() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListEstado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Estado>> token = new TypeToken<List<Estado>>() {
		};
		List<Estado> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToEstatus(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Estatus responseObject = gson.fromJson(response, Estatus.class);
		itemReturn.setSingle((responseObject.getIdEstatus() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListEstatus(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Estatus>> token = new TypeToken<List<Estatus>>() {
		};
		List<Estatus> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToGeolocalizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Geolocalizacion responseObject = gson.fromJson(response, Geolocalizacion.class);
		itemReturn.setSingle((responseObject.getIdGeolocalizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListGeolocalizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Geolocalizacion>> token = new TypeToken<List<Geolocalizacion>>() {
		};
		List<Geolocalizacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToGrupoSanguineo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		GrupoSanguineo responseObject = gson.fromJson(response, GrupoSanguineo.class);
		itemReturn.setSingle((responseObject.getIdGrupoSanguineo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListGrupoSanguineo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<GrupoSanguineo>> token = new TypeToken<List<GrupoSanguineo>>() {
		};
		List<GrupoSanguineo> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public Horario createResponseToHorario(String response) {
		Horario responseObject = gson.fromJson(response, Horario.class);
		return responseObject == null || responseObject.getIdHorario() == null ? null : responseObject;
	}

	public List<Horario> createResponseToListHorario(String response) {
		TypeToken<List<Horario>> token = new TypeToken<List<Horario>>() {};
		List<Horario> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public Incidencia createResponseToIncidencias(String response) {
		Incidencia responseObject = gson.fromJson(response, Incidencia.class);
		return responseObject == null || responseObject.getIdIncidencia() == null ? null : responseObject;
	}

	public List<Incidencia> createResponseToListIncidencias(String response) {
		TypeToken<List<Incidencia>> token = new TypeToken<List<Incidencia>>() {
		};
		List<Incidencia> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public Modulo createResponseToModulo(String response) {
		Modulo responseObject = gson.fromJson(response, Modulo.class);
		return responseObject == null || responseObject.getIdModulo() == null ? null : responseObject;
	}

	public List<Modulo> createResponseToListModulo(String response) {
		TypeToken<List<Modulo>> token = new TypeToken<List<Modulo>>() {};
		List<Modulo> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	
	public ModuloUsuario createResponseToModuloUsuario(String response) {
		ModuloUsuario responseObject = gson.fromJson(response, ModuloUsuario.class);
		return responseObject == null || responseObject.getIdModuloUsuario() == null ? null : responseObject;
	}

	public List<ModuloUsuario> createResponseToListModuloUsuario(String response) {
		TypeToken<List<ModuloUsuario>> token = new TypeToken<List<ModuloUsuario>>() {
		};
		List<ModuloUsuario> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public Municipio createResponseToMunicipio(String response) {
		Municipio responseObject = gson.fromJson(response, Municipio.class);
		return responseObject == null || responseObject.getIdMunicipio() == null ? null : responseObject;
	}

	public List<Municipio> createResponseToListMunicipio(String response) {
		TypeToken<List<Municipio>> token = new TypeToken<List<Municipio>>() {};
		List<Municipio> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public CtrlRestService createResponseToOficina(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Oficina responseObject = gson.fromJson(response, Oficina.class);
		itemReturn.setSingle((responseObject.getIdOficina() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListOficina(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Oficina>> token = new TypeToken<List<Oficina>>() {
		};
		List<Oficina> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Organizacion responseObject = gson.fromJson(response, Organizacion.class);
		itemReturn.setSingle((responseObject.getIdOrganizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Organizacion>> token = new TypeToken<List<Organizacion>>() {
		};
		List<Organizacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToPais(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Pais responseObject = gson.fromJson(response, Pais.class);
		itemReturn.setSingle((responseObject.getIdPais() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListPais(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Pais>> token = new TypeToken<List<Pais>>() {
		};
		List<Pais> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToPeriodo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Periodo responseObject = gson.fromJson(response, Periodo.class);
		itemReturn.setSingle((responseObject.getIdPeriodo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListPeriodo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Periodo>> token = new TypeToken<List<Periodo>>() {
		};
		List<Periodo> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToPersona(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Persona responseObject = gson.fromJson(response, Persona.class);
		itemReturn.setSingle((responseObject.getIdPersona() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListPersona(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Persona>> token = new TypeToken<List<Persona>>() {
		};
		List<Persona> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToPoliticaHorario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		PoliticaHorario responseObject = gson.fromJson(response, PoliticaHorario.class);
		itemReturn.setSingle((responseObject.getIdPoliticaHorario() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListPoliticaHorario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<PoliticaHorario>> token = new TypeToken<List<PoliticaHorario>>() {
		};
		List<PoliticaHorario> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToPoliticas(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Politicas responseObject = gson.fromJson(response, Politicas.class);
		itemReturn.setSingle((responseObject.getIdPoliticas() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListPoliticas(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Politicas>> token = new TypeToken<List<Politicas>>() {
		};
		List<Politicas> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public Rol createResponseToRol(String response) {
		Rol responseObject = gson.fromJson(response, Rol.class);
		return responseObject.getIdRol() == null ? null : responseObject;
	}

	public List<Rol> createResponseToListRol(String response) {
		TypeToken<List<Rol>> token = new TypeToken<List<Rol>>() {
		};
		List<Rol> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public CtrlRestService createResponseToSexo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Sexo responseObject = gson.fromJson(response, Sexo.class);
		itemReturn.setSingle((responseObject.getIdSexo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListSexo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Sexo>> token = new TypeToken<List<Sexo>>() {
		};
		List<Sexo> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToTelefono(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Telefono responseObject = gson.fromJson(response, Telefono.class);
		itemReturn.setSingle((responseObject.getIdTelefono() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListTelefono(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Telefono>> token = new TypeToken<List<Telefono>>() {
		};
		List<Telefono> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public Usuario createResponseToUsuario(String response) {
		Usuario responseObject = gson.fromJson(response, Usuario.class);
		return responseObject == null || responseObject.getIdUsuario() == null ? null : responseObject;
	}

	public List<Usuario> createResponseToListUsuario(String response) {
		TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>() {};
		List<Usuario> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public UsuarioIncidencia createResponseToUsuarioIncidencia(String response) {
		UsuarioIncidencia responseObject = gson.fromJson(response, UsuarioIncidencia.class);
		return responseObject == null || responseObject.getIdUsuarioIncidencia() == null ? null : responseObject;
	}

	public List<UsuarioIncidencia> createResponseToListUsuarioIncidencia(String response) {
		TypeToken<List<UsuarioIncidencia>> token = new TypeToken<List<UsuarioIncidencia>>() {};
		List<UsuarioIncidencia> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

	public ZonaHorario createResponseToZonaHorario(String response) {
		ZonaHorario responseObject = gson.fromJson(response, ZonaHorario.class);
		return responseObject == null || responseObject.getIdZonaHorario() == null ? null : responseObject;
	}

	public List<ZonaHorario> createResponseToListZonaHorario(String response) {
		TypeToken<List<ZonaHorario>> token = new TypeToken<List<ZonaHorario>>() {
		};
		List<ZonaHorario> responseObject = gson.fromJson(response, token.getType());
		return (responseObject.size() == 0) ? null : responseObject;
	}

}
