package com.came.control.web.vm.colaboradores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Window;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.beans.CapturadorManualAsistencia;
import com.came.control.model.beans.DiaCapturado;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class AgregaIncidenciaManualVM extends LayerWebOperaciones {

	private static final long serialVersionUID = 5170566493846834579L;

	@Wire("#agregaIncidenciaManualMD")
	private Window agregaIncidenciaManualMD;
	@Wire("#agregaIncidenciaManualMD, #btnVerHorario")
	private Button btnVerHorario;
	@Wire("#agregaIncidenciaManualMD, #btnFindUsr")
	private Button btnFindUsr;	
	@Wire("#agregaIncidenciaManualMD, #gridEditor")
	private Grid gridEditor;
	@Wire("#agregaIncidenciaManualMD, #intLunes")
	private Intbox intLunes;
	@Wire("#agregaIncidenciaManualMD, #intMartes")
	private Intbox intMartes;
	@Wire("#agregaIncidenciaManualMD, #intMiercoles")
	private Intbox intMiercoles;
	@Wire("#agregaIncidenciaManualMD, #intJueves")
	private Intbox intJueves;
	@Wire("#agregaIncidenciaManualMD, #intViernes")
	private Intbox intViernes;
	@Wire("#agregaIncidenciaManualMD, #intSabado")
	private Intbox intSabado;
	@Wire("#agregaIncidenciaManualMD, #intDomi")
	private Intbox intDomi;

	
	
	

	private String globalCommandName;

	private List<Asistencia> asistenciaAdminList;
//	private Asistencia asistenciaAdminSelected;
	
	private List<Incidencia> incidenciasCatalogo;
	private Incidencia incidenciaSeleccionada;
	private Organizacion org;
	
	//*****************
	private CapturadorManualAsistencia capturador;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("org") Organizacion orgIn) {
		globalCommandName = itemFinder;
		org = orgIn;
		asistenciaAdminList = new ArrayList<Asistencia>();
		incidenciasCatalogo = incidenciasRest.getByOrganizacion(orgIn);
//		asistenciaAdminSelected = new Asistencia();
//		asistenciaAdminSelected.setUsuario(new Usuario());
		nuevoItem();
	}
	
	@Command
	@NotifyChange({"capturador"})
	public void nuevoRegistro() {
		nuevoItem();
	}
	
	
	@Command
	@NotifyChange({"capturador", "asistenciaAdminList"})
	public void agregarRegistro() {
		MessageControl validador = validarFormulario();
		if (validador == null) {
			Asistencia newItem = null;
			if(!capturador.getLunes().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getLunes(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getMartes().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getMartes(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getMiercoles().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getMiercoles(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getJueves().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getJueves(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getViernes().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getViernes(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getSabado().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getSabado(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			if(!capturador.getDomingo().isVmDisabled()) {
				newItem = createAsistenciaObject(capturador.getDomingo(), capturador.getFechaActualizacion(), capturador.getUsuario());
				newItem = asistenciaRest.save(newItem);
				asistenciaAdminList.add(newItem);
			}
			Clients.showNotification("Asistencia registrada", Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
			
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
	}
	
	private Asistencia createAsistenciaObject(DiaCapturado diaObject, Date fechaActualizacion, Usuario usr) {
		Asistencia newItem = new Asistencia();
		newItem.setFechaActualizacion(ctrlUtils.convertirCalendarToString(ctrlUtils.convertirDateToCalendar(fechaActualizacion), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS));
		newItem.setFinalizado(true);
		newItem.setIncidencias(diaObject.getIncidencia());
		newItem.setOrganizacion(org);
		newItem.setHrsTrabajo(diaObject.getHrs());
		newItem.setObservaciones(diaObject.getObservacion());
		//newItem.setRegistro();
		newItem.setUsuario(usr);
		newItem.setRegistroDias(new Gson().toJson(diaObject));
		return newItem;
	}
	
	private MessageControl validarFormulario() {
		MessageControl ctrlMessage = new MessageControl();
		if(capturador.getUsuario() == null || capturador.getUsuario().getIdUsuario() == null) {
			ctrlMessage.setComponentZk(btnFindUsr);
			ctrlMessage.setMensaje("Debe seleccionar un usuario.");
			return ctrlMessage;
		}
		
		
		List<Asistencia> list = asistenciaRest.getByUsrAndDateWeek(capturador.getLunes().getFechaDate(), capturador.getDomingo().getFechaDate(), capturador.getUsuario().getIdUsuario(), org.getIdOrganizacion());
		if(list != null && list.size() > 0) {
			ctrlMessage.setMensaje("Este usuario ya ha sido registrado en esta semana");
			return ctrlMessage;
		}
		
		
		String validador = null;
		
		if(!capturador.getLunes().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getLunes().getHrs(), capturador.getLunes().getDiaNombre());
			if(validador != null) {
				intLunes.setFocus(true);
				ctrlMessage.setComponentZk(intLunes);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getMartes().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getMartes().getHrs(), capturador.getMartes().getDiaNombre());
			if(validador != null) {
				intMartes.setFocus(true);
				ctrlMessage.setComponentZk(intMartes);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getMiercoles().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getMiercoles().getHrs(), capturador.getMiercoles().getDiaNombre());
			if(validador != null) {
				intMiercoles.setFocus(true);
				ctrlMessage.setComponentZk(intMiercoles);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getJueves().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getJueves().getHrs(), capturador.getJueves().getDiaNombre());
			if(validador != null) {
				intJueves.setFocus(true);
				ctrlMessage.setComponentZk(intJueves);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getViernes().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getViernes().getHrs(), capturador.getViernes().getDiaNombre());
			if(validador != null) {
				intViernes.setFocus(true);
				ctrlMessage.setComponentZk(intViernes);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getSabado().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getSabado().getHrs(), capturador.getSabado().getDiaNombre());
			if(validador != null) {
				intSabado.setFocus(true);
				ctrlMessage.setComponentZk(intSabado);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		
		if(!capturador.getDomingo().isVmDisabled()) {
			validador = validaHoraIngresada(capturador.getDomingo().getHrs(), capturador.getDomingo().getDiaNombre());
			if(validador != null) {
				intDomi.setFocus(true);
				ctrlMessage.setComponentZk(intDomi);
				ctrlMessage.setMensaje(validador);
				return ctrlMessage;
			}
		}
		return null;
	}
	
	private String validaHoraIngresada(Integer valor, String dia) {
		if(valor == null)
			return "Valor invalido para las hrs del dia <b>" + dia + ".</b><br>Intente nuevamente.";
		if(valor < 0)
			return "Ingrese hrs validas para el dia <b>" + dia + ".</b><br>Valor ingresado: <b>" + valor + "</b>.";
		return null;
	}
	
	private void nuevoItem() {
		capturador = new CapturadorManualAsistencia();
		capturador.setUsuario(new Usuario());
		capturador.setFechaActualizacion(new Date());
		capturador.setFechaRegistro(new Date());
		
		Map<String, DiaCapturado> hash = obtenerfechaDiasDeSemana();
		
		capturador.setLunes(hash.get("lunes"));
		capturador.setMartes(hash.get("martes"));
		capturador.setMiercoles(hash.get("miercoles"));
		capturador.setJueves(hash.get("jueves"));
		capturador.setViernes(hash.get("viernes"));
		capturador.setSabado(hash.get("sabado"));
		capturador.setDomingo(hash.get("domingo"));
		
		if(btnVerHorario != null)
			btnVerHorario.setVisible(false);
		if(btnFindUsr != null)
			Clients.showNotification("Seleccione un usuario.", Clients.NOTIFICATION_TYPE_INFO, btnFindUsr, null, 0);
			
	}
	
	private DiaCapturado crearDiaObject(Calendar calendar, Integer diaDate) {
		calendar.set(Calendar.DAY_OF_MONTH, diaDate);
		diaDate = calendar.get(Calendar.DAY_OF_MONTH);
		String diaName = getDiaDeLaSemana(calendar).toLowerCase();
		
		DiaCapturado diaObject = new DiaCapturado();
		diaObject.setDiaFecha(diaDate);
		diaObject.setDiaNombre(limpiarAcentos(diaName)/*diaName*/);
		diaObject.setFechaCalendar(calendar);
		diaObject.setFechaDate(calendar.getTime());
		diaObject.setTagDay(diaName.toUpperCase() + " " + (diaDate.toString().length() == 1 ? "0" + diaDate : diaDate));
		diaObject.setLunesFinded(diaName.toLowerCase().equals("lunes") ? true : false);
		diaObject.setVmDisabled(true);
		
		return diaObject;
	}
	
	private Map<String, DiaCapturado> obtenerfechaDiasDeSemana(){
		Calendar calendar = Calendar.getInstance();
		Map<String, DiaCapturado> hash = new HashMap<String, DiaCapturado>();
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		Integer diaNumericoInicial = calendar.get(Calendar.DAY_OF_MONTH);
		
		while (true) {
			DiaCapturado diaObject = crearDiaObject(calendar, day);
			hash.put(diaObject.getDiaNombre(), diaObject);
			day = day - 1;
			if(diaObject.isLunesFinded())
				break;
		}
		
		while (true) {
			diaNumericoInicial ++;
			DiaCapturado diaObject = crearDiaObject(calendar, diaNumericoInicial);
			if(diaObject.isLunesFinded())
				break;
			hash.put(diaObject.getDiaNombre(), diaObject);
		}
		return hash;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		Clients.showNotification("Seleccione un usuario.", Clients.NOTIFICATION_TYPE_INFO, btnFindUsr, null, 0);
		//gridEditor.set
	}

	@Command
	public void transfer() {
		agregaIncidenciaManualMD.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		//args.put("usr", modalColaborador);
		if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty()))
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		else
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);

	}

	@Command
	public void closeDialog() {
		if (agregaIncidenciaManualMD != null) {
			agregaIncidenciaManualMD.detach();
		}
	}

	
	@Command
	public void verHorario() {
		if(capturador.getUsuario() == null || capturador.getUsuario().getIdUsuario() == null) {
			Clients.showNotification("Seleccione un usuario previamente", Clients.NOTIFICATION_TYPE_WARNING, btnFindUsr, null, 0);
			return ;
		}
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "");
		inputParams.put("selectedOficina", capturador.getUsuario().getOficina());
		inputParams.put("readOnly", true);
		inputParams.put("buttonVisible", false);
		inputParams.put("org", org);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/configurarHorario.zul", inputParams);
		productoModalView.doModal();
	}
	
	@Command
	public void clickedBuscador() {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "showColaboradorSelected");
		inputParams.put("org", org);

		Window productoModalView = ctrlUtils.createModelDialogWithParams("/modal/buscadorColaboradores.zul",
				inputParams);
		productoModalView.doModal();
	}
	
	
	
	
	
	
	
	@GlobalCommand
	@NotifyChange({ "capturador" })
	public void showColaboradorSelected(@BindingParam("usr") Usuario usr) {
		capturador.setUsuario(usr);
		
		List<Horario> listHorario = horarioRest.getByUsuario(capturador.getUsuario());
		if(listHorario != null) {
			for (Horario item : listHorario) {
				if(limpiarAcentos(item.getDia().toLowerCase()).equals("lunes")) {
					capturador.getLunes().setVmDisabled(item.getDescanso());
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("martes")) {
					capturador.getMartes().setVmDisabled(item.getDescanso());
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("miercoles")) {
					capturador.getMiercoles().setVmDisabled(item.getDescanso());		
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("jueves")) {
					capturador.getJueves().setVmDisabled(item.getDescanso());
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("viernes")) {
					capturador.getViernes().setVmDisabled(item.getDescanso());
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("sabado")) {
					capturador.getSabado().setVmDisabled(item.getDescanso());
				} else if(limpiarAcentos(item.getDia().toLowerCase()).equals("domingo")) {
					capturador.getDomingo().setVmDisabled(item.getDescanso());
				}
			}
		}
		btnVerHorario.setVisible(true);
	}
	
	
	
	
	
	public List<Asistencia> getAsistenciaAdminList() {
		return asistenciaAdminList;
	}

	public void setAsistenciaAdminList(List<Asistencia> asistenciaAdminList) {
		this.asistenciaAdminList = asistenciaAdminList;
	}

	

	public List<Incidencia> getIncidenciasCatalogo() {
		return incidenciasCatalogo;
	}

	public void setIncidenciasCatalogo(List<Incidencia> incidenciasCatalogo) {
		this.incidenciasCatalogo = incidenciasCatalogo;
	}

	public Incidencia getIncidenciaSeleccionada() {
		return incidenciaSeleccionada;
	}

	public void setIncidenciaSeleccionada(Incidencia incidenciaSeleccionada) {
		this.incidenciaSeleccionada = incidenciaSeleccionada;
	}


	public CapturadorManualAsistencia getCapturador() {
		return capturador;
	}


	public void setCapturador(CapturadorManualAsistencia capturador) {
		this.capturador = capturador;
	}

	
	
	
}
