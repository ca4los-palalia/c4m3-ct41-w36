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
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@VariableResolver({ DelegatingVariableResolver.class })
public class IncidenciasVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;
	
	@Wire("#inciZul, #firedUp")
	private Textbox firedUp;
	@Wire("#inciZul, #inciWinForm, #inciNombreTxt")
	private Textbox inciNombreTxt;
	@Wire("#inciZul, #inciWinForm, #inciClaveTxt")
	private Textbox inciClaveTxt;
	@Wire("#inciZul, #inciWinForm, #inciDescInt")
	private Intbox inciDescInt;
	@Wire("#inciZul, #inciWinForm, #inciDescTxt")
	private Textbox inciDescTxt;
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		incidencias = incidenciasRest.getByOrganizacion(organizacion);
		
		initProperties();
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		inciNombreTxt.setFocus(true);
	}
	
	@Command
	@NotifyChange({"incidenciaSelected"})
	public void clickedIncidenciaNuevo() {
		initProperties();
	}
	
	@Command
	@NotifyChange({"incidenciaSelected", "incidencias"})
	public void clickedIncidenciaGuardar() {
		MessageControl validador = validarFormulario();
		if(validador == null) {
			
			String message = "Incidencia <b>" + incidenciaSelected.getNombre() + "</b>" + (incidenciaSelected.getIdIncidencia() == null ? " ha sido creada" : " ha sido modificada")  ;
			boolean addToList = incidenciaSelected.getIdIncidencia() == null ? true : false;
			incidenciaSelected = incidenciasRest.save(incidenciaSelected);
			
			if(addToList)
				incidencias.add(incidenciaSelected);
			else
				incidenciaSelected = iteratorList.getIncidenciaById(incidencias, incidenciaSelected.getIdIncidencia());
			Clients.showNotification(message, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
	}
	
	@Command
	@NotifyChange({"incidenciaSelected", "incidencias"})
	public void eliminarIncidencia(@BindingParam("index") Integer index) {
//		incidenciaSelected = incidencias.get(index);
//		incidencias.remove(incidenciaSelected);
		incidenciasRest.delete(incidenciaSelected);
		incidencias = incidenciasRest.getByOrganizacion(organizacion);
	}
	
	@Command
	@NotifyChange({"incidenciaSelected", "incidencias"})
	public void updateColorChooser() {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(firedUp.getText()).getAsJsonObject();
		String color = obj.get("color").getAsString();
		Long idRow = obj.get("idRow").getAsLong();
		incidenciaSelected = iteratorList.getIncidenciaById(incidencias, idRow);
		incidenciaSelected.setColor(color);
		firedUp.setText("");
	}
	
	
	
	private void initProperties() {
		incidenciaSelected = new Incidencia();
		incidenciaSelected.setOrganizacion(organizacion);
	}
	
	private MessageControl validarFormulario(){
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_INCIDENCIA_TITLE);
		if(incidenciaSelected == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_INICIALIZADO);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
		if(incidenciaSelected.getNombre() == null || incidenciaSelected.getNombre().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_NOMBRE);
			ctrlMessage.setComponentZk(inciNombreTxt);
			inciNombreTxt.setFocus(true);
			return ctrlMessage;
		}
		
		Incidencia localIncidencia = null;
		
		if(incidenciaSelected.getIdIncidencia() == null) {
			localIncidencia = incidenciasRest.getByNombre(incidenciaSelected.getNombre(), organizacion);
			if (localIncidencia != null && localIncidencia.getNombre().equals(incidenciaSelected.getNombre())) {
				ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
				ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_DUPLICADO);
				ctrlMessage.setComponentZk(inciNombreTxt);
				inciNombreTxt.setFocus(true);
				return ctrlMessage;
			}
		}
		
		
		if(incidenciaSelected.getClave() == null || incidenciaSelected.getClave().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_CLAVE);
			ctrlMessage.setComponentZk(inciClaveTxt);
			inciClaveTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(incidenciaSelected.getIdIncidencia() == null) {
			localIncidencia = incidenciasRest.getByClave(incidenciaSelected.getClave(), organizacion.getIdOrganizacion());
			if (localIncidencia != null && localIncidencia.getClave().equals(incidenciaSelected.getClave())) {
				ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
				ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_DUPLICADA);
				ctrlMessage.setComponentZk(inciClaveTxt);
				inciClaveTxt.setFocus(true);
				return ctrlMessage;
			}
		}
		
		
//		if(incidenciaSelected.getDescuentoPorcentaje() == null) {
//			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
//			ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_DESCUENTO);
//			ctrlMessage.setComponentZk(inciDescInt);
//			inciDescInt.setFocus(true);
//			return ctrlMessage;
//		}
//		
//		if(incidenciaSelected.getDescuentoPorcentaje() < 0) {
//			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
//			ctrlMessage.setMensaje(ConstMensajes.VALIDA_INCIDENCIA_DESCUENTO_INVALIDO);
//			ctrlMessage.setComponentZk(inciDescInt);
//			inciDescInt.setFocus(true);
//			return ctrlMessage;
//		}
		return null;
		
	}
}
