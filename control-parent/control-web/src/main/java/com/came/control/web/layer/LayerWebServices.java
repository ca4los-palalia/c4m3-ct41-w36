package com.came.control.web.layer;

import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.came.control.beans.funciones.CtrlUtilString;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.beans.funciones.CtrlUtilsSession;
import com.came.control.beans.funciones.Funciones;
import com.came.control.beans.funciones.PasswordGenerator;
import com.came.control.model.services.UsuarioService;
import com.came.control.model.util.IteratorList;
import com.came.control.web.services.AsistenciaRest;
import com.came.control.web.services.BancoRest;
import com.came.control.web.services.CalendariosRest;
import com.came.control.web.services.ConfiguracionRest;
import com.came.control.web.services.DatosGeneralesRest;
import com.came.control.web.services.DireccionRest;
import com.came.control.web.services.EmergenciaRest;
import com.came.control.web.services.EscolaridadRest;
import com.came.control.web.services.EstadoCivilRest;
import com.came.control.web.services.EstadoRest;
import com.came.control.web.services.EstatusRest;
import com.came.control.web.services.GeolocalizacionRest;
import com.came.control.web.services.GrupoSanguineoRest;
import com.came.control.web.services.HorarioRest;
import com.came.control.web.services.IncidenciasRest;
import com.came.control.web.services.MetodoPagoRest;
import com.came.control.web.services.ModuloRest;
import com.came.control.web.services.ModuloUsuarioRest;
import com.came.control.web.services.MunicipioRest;
import com.came.control.web.services.NacionalidadRest;
import com.came.control.web.services.OficinaRest;
import com.came.control.web.services.OrganizacionRest;
import com.came.control.web.services.PaisRest;
import com.came.control.web.services.PeriodoRest;
import com.came.control.web.services.PersonaRest;
import com.came.control.web.services.PoliticaHorarioRest;
import com.came.control.web.services.PoliticasRest;
import com.came.control.web.services.RolRest;
import com.came.control.web.services.SexoRest;
import com.came.control.web.services.TelefonoRest;
import com.came.control.web.services.UsuarioIncidenciaRest;
import com.came.control.web.services.UsuarioRest;
import com.came.control.web.services.ZonaHorarioRest;
@Repository
public class LayerWebServices extends LayerWebTags {
	
	private static final long serialVersionUID = -3036199226989462765L;
	
	@WireVariable
	protected AsistenciaRest asistenciaRest;
	@WireVariable
	protected CalendariosRest calendariosRest;
	@WireVariable
	protected ConfiguracionRest configuracionRest;
	@WireVariable
	protected DatosGeneralesRest datosGeneralesRest;
	@WireVariable
	protected DireccionRest direccionRest;
	@WireVariable
	protected EmergenciaRest emergenciaRest;
	@WireVariable
	protected EscolaridadRest escolaridadRest;
	@WireVariable
	protected EstadoCivilRest estadoCivilRest;
	@WireVariable
	protected EstadoRest estadoRest;
	@WireVariable
	protected EstatusRest estatusRest;
	@WireVariable
	protected GeolocalizacionRest geolocalizacionRest;
	@WireVariable
	protected GrupoSanguineoRest grupoSanguineoRest;
	@WireVariable
	protected HorarioRest horarioRest;
	@WireVariable
	protected IncidenciasRest incidenciasRest;
	@WireVariable
	protected ModuloRest moduloRest;
	@WireVariable
	protected ModuloUsuarioRest moduloUsuarioRest;
	@WireVariable
	protected MunicipioRest municipioRest;
	@WireVariable
	protected NacionalidadRest nacionalidadRest;
	@WireVariable
	protected OficinaRest oficinaRest;
	@WireVariable
	protected OrganizacionRest organizacionRest;
	@WireVariable
	protected PaisRest paisRest;
	@WireVariable
	protected PeriodoRest periodoRest;
	@WireVariable
	protected PersonaRest personaRest;
	@WireVariable
	protected PoliticaHorarioRest politicaHorarioRest;
	@WireVariable
	protected PoliticasRest politicasRest;
	@WireVariable
	protected RolRest rolRest;
	@WireVariable
	protected SexoRest sexoRest;
	@WireVariable
	protected TelefonoRest telefonoRest;
	@WireVariable
	protected UsuarioRest usuarioRest;
	@WireVariable
	protected UsuarioIncidenciaRest usuarioIncidenciaRest;
	@WireVariable
	protected ZonaHorarioRest zonaHorarioRest;
	@WireVariable
	protected BancoRest bancoRest;
	@WireVariable
	protected MetodoPagoRest metodoPagoRest;
	
	@WireVariable
	public Funciones funciones;
	@WireVariable
	public CtrlUtils ctrlUtils;
	@WireVariable
	public CtrlUtilString ctrlUtilString;
	@WireVariable
	public CtrlUtilsSession ctrlUtilsSession;
	@WireVariable
	public IteratorList iteratorList;
	@WireVariable
	public PasswordGenerator passwordGenerator;
	
	
	@WireVariable
	public UsuarioService usuarioService;
	

}
