package com.came.control.web.vm.controlpanel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Textbox;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class ZonaHorarioVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;
	
	@Wire("#zhZul, #zhWinForm, #zhUtcTxt")
	private Textbox zhUtcTxt;
	@Wire("#zhZul, #zhWinForm, #zhZonaHorarioTxt")
	private Textbox zhZonaHorarioTxt;
	@Wire("#zhZul, #zhWinForm, #zhPaisTxt")
	private Textbox zhPaisTxt;
	@Wire("#zhZul, #zhWinForm, #zhMainCountriesTxt")
	private Textbox zhMainCountriesTxt;
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		zonasHorarios = zonaHorarioRest.getByOrganizacion(organizacion);
		
		initProperties();
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		zhUtcTxt.setFocus(true);
	}
	
	@Command
	@NotifyChange({"zonaHorarioSelected"})
	public void clickedZonaHorariaNuevo() {
		initProperties();
	}
	
	@Command
	@NotifyChange({"zonaHorarioSelected", "zonasHorarios"})
	public void clickedZonaHorariaGuardar() {
		MessageControl validador = validarFormulario();
		if(validador == null) {
			
			String message = "Zona horario <b>" + zonaHorarioSelected.getUtc() + "</b> " + (zonaHorarioSelected.getIdZonaHorario() == null ? " ha sido creada" : " ha sido modificada");
			
			
			boolean addToList = zonaHorarioSelected.getIdZonaHorario() == null ? true : false;
			zonaHorarioSelected = zonaHorarioRest.save(zonaHorarioSelected);
			
			if(addToList)
				zonasHorarios.add(zonaHorarioSelected);
			else
				zonaHorarioSelected = iteratorList.getZonaHorarioById(zonasHorarios, zonaHorarioSelected.getIdZonaHorario());
			Clients.showNotification(message, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
			
	}
	
	@Command
	@NotifyChange({"zonaHorarioSelected", "zonasHorarios"})
	public void eliminarZOnaHoraria(@BindingParam("index") Integer index) {
//		incidenciaSelected = incidencias.get(index);
//		incidencias.remove(incidenciaSelected);
		zonaHorarioRest.delete(zonaHorarioSelected);
		zonasHorarios = zonaHorarioRest.getByOrganizacion(organizacion);
	}
	
	
	private void initProperties() {
		zonaHorarioSelected = new ZonaHorario();
		zonaHorarioSelected.setOrganizacion(organizacion);
	}
	
	private MessageControl validarFormulario(){
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_ZONA_HORARIO_TITLE);
		if(zonaHorarioSelected == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ZONA_HORARIO_INICIALIZADO);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
		if(zonaHorarioSelected.getUtc() == null || zonaHorarioSelected.getUtc().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ZONA_HORARIO_UTC);
			ctrlMessage.setComponentZk(zhUtcTxt);
			zhUtcTxt.setFocus(true);
			return ctrlMessage;
		}
		
		
		if(zonaHorarioSelected.getZonaHoraria() == null || zonaHorarioSelected.getZonaHoraria().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ZONA_HORARIO_ZA);
			ctrlMessage.setComponentZk(zhZonaHorarioTxt);
			zhZonaHorarioTxt.setFocus(true);
			return ctrlMessage;
		}
		
		ZonaHorario localzonaHorario  = null;
		
		if(zonaHorarioSelected.getIdZonaHorario() == null) {
			localzonaHorario = zonaHorarioRest.getByZonaHorario(zonaHorarioSelected.getZonaHoraria(), organizacion);
			if (localzonaHorario != null && localzonaHorario.getZonaHoraria().equals(zonaHorarioSelected.getZonaHoraria())) {
				ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
				ctrlMessage.setMensaje(ConstMensajes.VALIDA_ZONA_HORARIO_ZA_DUPLICADA);
				ctrlMessage.setComponentZk(zhZonaHorarioTxt);
				zhZonaHorarioTxt.setFocus(true);
				return ctrlMessage;
			}
		}
		
		if(zonaHorarioSelected.getPais() == null || zonaHorarioSelected.getPais().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ZONA_HORARIO_PAIS);
			ctrlMessage.setComponentZk(zhPaisTxt);
			zhPaisTxt.setFocus(true);
			return ctrlMessage;
		}
		return null;
		
	}
}
