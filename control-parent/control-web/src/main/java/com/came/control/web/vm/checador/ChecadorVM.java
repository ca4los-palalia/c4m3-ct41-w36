package com.came.control.web.vm.checador;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import com.came.control.beans.ConfigureAccuracy;
import com.came.control.beans.ConfigureHabilitarFuncion;
import com.came.control.beans.RegistroEncapsulado;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class ChecadorVM extends LayerWebOperaciones {
	
	private static final long serialVersionUID = 313977001812349337L;
	
	
	@Wire("#checadorzul, #fly")
	private Textbox textLocation;
	
	@Wire("#checadorzul, #blMainChecker, #blCtrlRegistros, #btnJornadaEntrada")
	private Button btnJornadaEntrada;
	@Wire("#checadorzul, #blMainChecker, #blCtrlRegistros, #btnJornadaSalida")
	private Button btnJornadaSalida;
	@Wire("#checadorzul, #blMainChecker, #blCtrlRegistros, #btnComidaSalida")
	private Button btnComidaSalida;
	@Wire("#checadorzul, #blMainChecker, #blCtrlRegistros, #btnComidaEntrada")
	private Button btnComidaEntrada;
	

	private Calendar actualFechaCalendar;
	private String actualStrFecha;
	private String actualStrHora;
	private Asistencia registroAsistencia;
	private String actualDia;
	private Horario horarioUsuarioSelected;
	private List<Horario> listHorarioUsuario;
	private List<UsuarioIncidencia> incidenciasUsuario;
	
	private String horaClickedJornadaEntrada;
	private String horaClickedJornadaSalida;
	private String horaClickedSaleAComer;
	private String horaClickedRegresaDeComer;
	
	private ConfigureAccuracy configureAccuracy;
	private ConfigureHabilitarFuncion cfgHabilita;
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		
		Configuracion cfg = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_ACCURACY, organizacion);
		configureAccuracy = new Gson().fromJson(cfg.getValor(), ConfigureAccuracy.class);
		cfg = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_MSG_INCIDENCIAS, organizacion);
		cfgHabilita = new Gson().fromJson(cfg.getValor(), ConfigureHabilitarFuncion.class);
		
		initProperties();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		btnJornadaEntrada.setSclass("btnFormatCheck btnStartCheckDisabled");
		btnJornadaEntrada.setDisabled(true);
		btnJornadaSalida.setSclass("btnFormatCheck btnStopCheckDisabled");
		btnJornadaSalida.setDisabled(true);
		
		btnComidaSalida.setSclass("btnFormatCheck btnStartCheckDisabled");
		btnComidaSalida.setDisabled(true);
		btnComidaEntrada.setSclass("btnFormatCheck btnStopCheckDisabled");
		btnComidaEntrada.setDisabled(true);
	}
	
	@Command
	@NotifyChange("*")
	public void listenerLocation() {
		String message = validarGeolocalizacion();
		
		if(message != null) {
			Clients.showNotification("<b>Geolocalizador:</b> " + message, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		registroAsistencia = asistenciaRest.getByUsrAndDate(new Date(), usuario.getIdUsuario(), organizacion.getIdOrganizacion());
		
		if(registroAsistencia != null) {
			RegistroEncapsulado registro = new Gson().fromJson(registroAsistencia.getRegistro(), RegistroEncapsulado.class);
			
			if(registro.getJornadaEntrada() != null) {
				horaClickedJornadaEntrada = getHoraFromFechaString(registro.getJornadaEntrada().getHoraRegistrada(), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
				
			} else {
				
			}
			
			if(registro.getJornadaSalida() != null) {
				horaClickedJornadaSalida = getHoraFromFechaString(registro.getJornadaSalida().getHoraRegistrada(), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
			} else {
				btnJornadaSalida.setSclass("btnFormatCheck btnStopCheck");
				btnJornadaSalida.setDisabled(false);
			}
			
			
			if(registro.getComidaSalida() != null) {//boton rojo
				horaClickedRegresaDeComer = getHoraFromFechaString(registro.getComidaSalida().getHoraRegistrada(), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
				
				if(registro.getComidaEntrada() != null) {
					btnComidaEntrada.setSclass("btnFormatCheck btnStopCheckDisabled");
					btnComidaEntrada.setDisabled(true);
				} else {
					btnComidaEntrada.setSclass("btnFormatCheck btnStopCheck");
					btnComidaEntrada.setDisabled(false);
				}
				
			} else {
				btnComidaSalida.setSclass("btnFormatCheck btnStartCheck");
				btnComidaSalida.setDisabled(false);
			}
			
			if(registro.getComidaEntrada() != null) {
				horaClickedSaleAComer = getHoraFromFechaString(registro.getComidaEntrada().getHoraRegistrada(), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
			} else {
				
			}
			
		} else {
			btnJornadaEntrada.setSclass("btnFormatCheck btnStartCheck");
			btnJornadaEntrada.setDisabled(false);
		}
		textLocation.setValue("");
	}
	
	@Command
	@NotifyChange({"diaActual", "fechaActual", "horaClickedJornadaEntrada"})
	public void pushJornadaEntradaBinder() {
		if(textLocation.getValue() != null&& !textLocation.getValue().isEmpty()) {
			actualFechaCalendar = ctrlUtils.convertirStringToCalendar(textLocation.getValue());
			textLocation.setValue("");
			clickedJornadaEntrada();
		}
	}
	
	@Command
	@NotifyChange({"diaActual", "fechaActual", "horaClickedJornadaSalida"})
	public void pushJornadaSalidaBinder() {
		if(textLocation.getValue() != null&& !textLocation.getValue().isEmpty()) {
			actualFechaCalendar = ctrlUtils.convertirStringToCalendar(textLocation.getValue());
			textLocation.setValue("");
			clickedJornadaSalida();
		}
	}
	
	@Command
	@NotifyChange({"diaActual", "fechaActual", "horaClickedSaleAComer", "horaClickedRegresaDeComer"})
	public void pushSalirAComerBinder() {
		if(textLocation.getValue() != null&& !textLocation.getValue().isEmpty()) {
			actualFechaCalendar = ctrlUtils.convertirStringToCalendar(textLocation.getValue());
			textLocation.setValue("");
			clickedSalirAComer();
		}
	}
	
	
	@Command
	@NotifyChange({"diaActual", "fechaActual", "horaClickedRegresaDeComer"})
	public void pushRegresarDeComerBinder() {
		if(textLocation.getValue() != null&& !textLocation.getValue().isEmpty()) {
			actualFechaCalendar = ctrlUtils.convertirStringToCalendar(textLocation.getValue());
			textLocation.setValue("");
			clickedRegresarDeComer();
		}
	}
	
	private void initProperties() {
		actualFechaCalendar = Calendar.getInstance();
		actualStrFecha = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
		actualStrHora = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM);
		
		actualDia = getDiaActual().toLowerCase();
		listHorarioUsuario = horarioRest.getByUsuario(usuario);
		for (Horario itemHorarioUsr : listHorarioUsuario) {
			if(itemHorarioUsr.getDia().toLowerCase().contentEquals(actualDia)) {
				horarioUsuarioSelected = itemHorarioUsr;
				break;
			}
		}
		registroAsistencia = asistenciaRest.getByUsrAndDate(actualFechaCalendar.getTime(), usuario.getIdUsuario(), organizacion.getIdOrganizacion());
		incidenciasUsuario = usuarioIncidenciaRest.getByUsuarioAndFecha(usuario, organizacion, actualFechaCalendar.getTime());
		
		
	}

	
	private void clickedJornadaEntrada() {
		String mensaje = "";
		if(listHorarioUsuario == null || listHorarioUsuario.size() == 0) {
			Clients.showNotification("El usuario <b>no cuenta con un horario<b> asignado.<br>Consulte al administrador", Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		if(horarioUsuarioSelected.getDescanso() == true) {
			Clients.showNotification("No es un dia laboral", Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		Calendar usrEntradaCalendar = builderCalendarFromTime(horarioUsuarioSelected.gethEntrada());
		
		if(registroAsistencia == null)
			registroAsistencia = new Asistencia();
		registroAsistencia.setFechaActualizacion(actualStrFecha);
		registroAsistencia.setOrganizacion(organizacion);
		registroAsistencia.setUsuario(usuario);
		
		long diferencia = actualFechaCalendar.getTime().getTime() - usrEntradaCalendar.getTime().getTime();
		long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
		
		String claveIncidencia = "";
		if(minutos > 15 && minutos < 31)
			claveIncidencia = ConstAtributos.INCIDENCIA_FR;
		else if(minutos > 30)
			claveIncidencia = ConstAtributos.INCIDENCIA_F;
		
		mensaje = registrarIncidencia(claveIncidencia);
		
		RegistroEncapsulado encapsulado = new RegistroEncapsulado();
		encapsulado.setJornadaEntrada(ctrlUtils.crearRegistro(false, actualStrFecha, "JornadaEntrada"));
		
		
		horaClickedJornadaEntrada = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM); 
		
		registroAsistencia.setRegistro(new Gson().toJson(encapsulado));
		registroAsistencia = asistenciaRest.save(registroAsistencia);
		
		btnJornadaEntrada.setSclass("btnFormatCheck btnStartCheckDisabled");
		btnJornadaEntrada.setDisabled(true);
		btnComidaSalida.setSclass("btnFormatCheck btnStartCheck");
		btnComidaSalida.setDisabled(false);
		
		btnJornadaSalida.setSclass("btnFormatCheck btnStopCheck");
		btnJornadaSalida.setDisabled(false);
		
		Clients.showNotification("Se ha registrado la <b>entrada de trabajo</b> a las <b>" + actualStrHora + "</b>" + (mensaje == null ? "" : "<br>" + mensaje), Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		
//		encapsulado.setJornadaSalida();
//		encapsulado.setComidaEntrada(comidaEntrada);
//		encapsulado.setComidaSalida();
		
	}
	
	private void clickedJornadaSalida() {
		Calendar usrSalidaCalendar = builderCalendarFromTime(horarioUsuarioSelected.gethSalida());
		long diferencia = usrSalidaCalendar.getTime().getTime() - actualFechaCalendar.getTime().getTime();
		long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
		
		if(minutos > 0) {
			String mensaje = "Faltan por trabajar "; 
			long hrs = TimeUnit.MILLISECONDS.toHours(diferencia);
			mensaje += hrs > 0 ? ("<b>" + hrs + "h:" + minutosRestantes(minutos, hrs) + "m.</b>") : "<b>" + minutos + " minutos</b>";
			Clients.showNotification("No puede finalizar la jornada.<br>" + mensaje, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		horaClickedJornadaSalida = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM); 
	}
	
	private long minutosRestantes(Long totalMinutos, Long hrs) {
		long minutosDeHoras = hrs * 60;
		return totalMinutos - minutosDeHoras;
	}
	
	private void clickedSalirAComer() {
		String mensaje = ""; 
		Calendar usrComidaSalirCalendar = builderCalendarFromTime(horarioUsuarioSelected.gethComidaEntrada());
		long diferencia = usrComidaSalirCalendar.getTime().getTime() - actualFechaCalendar.getTime().getTime();
		long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
		
		if(minutos > 0) {
			mensaje = "Faltan "; 
			long hrs = TimeUnit.MILLISECONDS.toHours(diferencia);
			mensaje += hrs > 0 ? ("<b>" + hrs + "h:" + minutosRestantes(minutos, hrs) + "m." ) + "</b>": "<b>" + minutos + " minutos</b>";
			mensaje += " para tomar un descanso.";
			Clients.showNotification("Aun no debe salir a comer.<br>" + mensaje, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		if(registroAsistencia == null || registroAsistencia.getRegistro() == null) {
			Clients.showNotification("Hubo un error al cargar la asistencia del usuario. Intente mas tarde.", Clients.NOTIFICATION_TYPE_ERROR, null, null, 0);
			return;
		}
		
		Calendar calendarFin = builderCalendarFromTime(horarioUsuarioSelected.gethComidaSalida());
		Calendar calendarIni = builderCalendarFromTime(horarioUsuarioSelected.gethComidaEntrada());
		
		diferencia = calendarFin.getTime().getTime() - calendarIni.getTime().getTime();
		long minutosDeComida = TimeUnit.MILLISECONDS.toMinutes(diferencia);
		
		if(minutos < (-1 * minutosDeComida) ) {
			mensaje = "Tiempo de comida inválido, tu horario es <b>" + horarioUsuarioSelected.gethComidaEntrada() + "</b> a <b>" + horarioUsuarioSelected.gethComidaSalida()
			+".</b><br>";
			mensaje += registrarIncidencia(ConstAtributos.INCIDENCIA_CF);
			horaClickedRegresaDeComer = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM); 
		}
		
		horaClickedSaleAComer = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM); 
		
		RegistroEncapsulado encapsulado = new Gson().fromJson(registroAsistencia.getRegistro(), RegistroEncapsulado.class);
		encapsulado.setComidaSalida(ctrlUtils.crearRegistro(false, actualStrFecha, "ComidaSalida"));
		if(!mensaje.isEmpty())
			encapsulado.setComidaEntrada(ctrlUtils.crearRegistro(false, actualStrFecha, "ComidaEntrada"));
		
		registroAsistencia.setRegistro(new Gson().toJson(encapsulado));
		registroAsistencia = asistenciaRest.save(registroAsistencia);
		
		
		btnComidaSalida.setSclass("btnFormatCheck btnStartCheckDisabled");
		btnComidaSalida.setDisabled(true);
		
		if(!mensaje.isEmpty()) {
			btnComidaEntrada.setSclass("btnFormatCheck btnStopCheckDisabled");
			btnComidaEntrada.setDisabled(true);
		} else {
			btnComidaEntrada.setSclass("btnFormatCheck btnStartCheck");
			btnComidaEntrada.setDisabled(false);
		}
		
		if(!mensaje.isEmpty())
			Clients.showNotification("Fuera del rango para registrar tiempo de comida.<br>" + mensaje, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
		else
			Clients.showNotification("Registro para salir a comer exitoso. Buen provecho.", Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		
	}
	
	private void clickedRegresarDeComer() {
		String mensaje = "";
		Calendar usrComidaRegresoCalendar = builderCalendarFromTime(horarioUsuarioSelected.gethComidaSalida());
		long diferencia = usrComidaRegresoCalendar.getTime().getTime() - actualFechaCalendar.getTime().getTime();
		long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
		
		if(minutos > 0) {
			mensaje = "Faltan "; 
			long hrs = TimeUnit.MILLISECONDS.toHours(diferencia);
			mensaje += hrs > 0 ? hrs + " hr(s)." : minutos + " minutos";
			mensaje += " de tu tiempo para comer.";
			Clients.showNotification(mensaje, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		} else
			mensaje = registrarIncidencia(ConstAtributos.INCIDENCIA_CT);
		
		horaClickedRegresaDeComer = ctrlUtils.convertirCalendarToString(actualFechaCalendar, ConstAtributos.FORMAT_DATE_HH_MM); 
		
		RegistroEncapsulado encapsulado = new Gson().fromJson(registroAsistencia.getRegistro(), RegistroEncapsulado.class);
		encapsulado.setComidaEntrada(ctrlUtils.crearRegistro(false, actualStrFecha, "ComidaEntrada"));
		registroAsistencia.setRegistro(new Gson().toJson(encapsulado));
		registroAsistencia = asistenciaRest.save(registroAsistencia);
		
		btnComidaEntrada.setSclass("btnFormatCheck btnStartCheckDisabled");
		btnComidaEntrada.setDisabled(true);
		
		Clients.showNotification(mensaje, Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
	}

	/**
	 * @param formato HH:mm
	 * @return Calendar con fecha actual y tiempo modificado de acuerdo al parametro
	 * **/
	private Calendar builderCalendarFromTime(String hora) {
		String [] splitter = hora.replace(":", "-").split("-");
		return ctrlUtils.getFechaControladaCalendarCambiarHr(actualFechaCalendar, new Integer(splitter[0]), new Integer(splitter[1]));
	}
	
	private String registrarIncidencia(String clave) {
		Incidencia incidencia = null;
		if(!clave.isEmpty())
			incidencia = (Incidencia)incidenciasRest.getByClave(clave, organizacion.getIdOrganizacion());
		
		if(incidencia != null) {
			UsuarioIncidencia usuarioIncidencia = new UsuarioIncidencia();
			usuarioIncidencia.setFecha(actualStrFecha);
			usuarioIncidencia.setIncidencia(incidencia);
			usuarioIncidencia.setOrganizacion(organizacion);
			usuarioIncidencia.setUsuario(usuario);
			usuarioIncidenciaRest.save(usuarioIncidencia);
			return "Se ha generado una incidencia de tipo <b>" + incidencia.getNombre() + " (" + incidencia.getClave() + ")</b>." + (cfgHabilita.isActivar() ? (  "<br><i>" + incidencia.getDescripcion() + "</i>" ) : "");
		}
		return null;
	}
	
	private String validarGeolocalizacion() {
		if(textLocation.getValue().isEmpty())
			return "No se puede obtener la ubicacion";
		
		if(usuario.getOficina() == null)
			return "No tienes asignado una oficina.<br>Contacte al administrador y vuelva a intentar mas tarde.";
		
		if(usuario.getOficina().getGeolocalizacion() == null)
			return "Tu oficina <b>no tiene asignado las coordenadas de geolocalizacion.<b><br>Contacte al administrador y vuelva a intentar mas tarde.";
		
		Geolocalizacion geo = new Gson().fromJson(textLocation.getValue(), Geolocalizacion.class);
		actualFechaCalendar = ctrlUtils.convertirStringToCalendar(geo.getCurrentTimeClient());
		try {
			String jsonInfo = new String(Base64.decodeBase64(geo.getDescripcion().getBytes()), "UTF-8");
			geo = splitterGeoDireccion(geo, jsonInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		float distanciaMts = ctrlUtils.haversineDistance(geo.getLatitud(), geo.getLongitud(), usuario.getOficina().getGeolocalizacion().getLatitud(), usuario.getOficina().getGeolocalizacion().getLongitud());
		if(Math.round(distanciaMts) > configureAccuracy.getMetros())
			return "Se encuentra fuera del rango.<br>Estas a <b>" + distanciaMts + " mts</b> del lugar";
		return null;
	}
	
	
	private String getHoraFromFechaString(String fechaString, String formatDate) {
		Calendar cal1 = ctrlUtils.convertirStringToCalendarWithFormat(fechaString, formatDate);
		return horaClickedJornadaEntrada = ctrlUtils.convertirCalendarToString(cal1, ConstAtributos.FORMAT_DATE_HH_MM);
	}
	
	
	
	/** GETTERS AND SETTERS**/
	public String getFechaActual() {
		return new SimpleDateFormat("yyyy-MM-dd").format(actualFechaCalendar.getTime());
	}
	
	public String getDiaActual() {
		return getDiaDeLaSemana(actualFechaCalendar);
	}

	public String getHoraClickedJornadaEntrada() {
		return horaClickedJornadaEntrada;
	}

	public void setHoraClickedJornadaEntrada(String horaClickedJornadaEntrada) {
		this.horaClickedJornadaEntrada = horaClickedJornadaEntrada;
	}

	public String getHoraClickedJornadaSalida() {
		return horaClickedJornadaSalida;
	}

	public void setHoraClickedJornadaSalida(String horaClickedJornadaSalida) {
		this.horaClickedJornadaSalida = horaClickedJornadaSalida;
	}

	public String getHoraClickedSaleAComer() {
		return horaClickedSaleAComer;
	}

	public void setHoraClickedSaleAComer(String horaClickedSaleAComer) {
		this.horaClickedSaleAComer = horaClickedSaleAComer;
	}

	public String getHoraClickedRegresaDeComer() {
		return horaClickedRegresaDeComer;
	}

	public void setHoraClickedRegresaDeComer(String horaClickedRegresaDeComer) {
		this.horaClickedRegresaDeComer = horaClickedRegresaDeComer;
	}

	public List<UsuarioIncidencia> getIncidenciasUsuario() {
		return incidenciasUsuario;
	}

	public void setIncidenciasUsuario(List<UsuarioIncidencia> incidenciasUsuario) {
		this.incidenciasUsuario = incidenciasUsuario;
	}
	
	
	/** END GETTERS AND SETTERS**/
}
