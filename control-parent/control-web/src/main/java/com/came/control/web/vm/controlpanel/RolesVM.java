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
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class RolesVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;
	
	@Wire("#rolZul, #rolWinForm, #rolNombreTxt")
	private Textbox rolNombreTxt;
	@Wire("#rolZul, #rolWinForm, #rolDescTxt")
	private Textbox rolDescTxt;
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		roles = rolRest.getByOrganizacion(organizacion);
		
		initProperties();
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		rolNombreTxt.setFocus(true);
	}
	
	@Command
	@NotifyChange({"rolSelected"})
	public void clickedRolNuevo() {
		initProperties();
	}
	
	@Command
	@NotifyChange({"rolSelected"})
	public void clickedRolGuardar() {
		MessageControl validador = validarFormulario();
		if(validador == null) {
			rolSelected = rolRest.save(rolSelected);
			String message = "El rol <b>" + rolSelected.getNombre() +  "</b> " + (rolSelected.getIdRol() == null ? " ha sido creado" : "ha sido modificado");
			Clients.showNotification(message, Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
	}
	@Command
	@NotifyChange({"rolSelected", "roles"})
	public void eliminarRol(@BindingParam("index") Integer index) {
		rolSelected = roles.get(index);
		roles.remove(rolSelected);
	}
	
	
	
	private void initProperties() {
		rolSelected = new Rol();
		rolSelected.setOrganizacion(organizacion);
	}
	
	private MessageControl validarFormulario(){
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_ROL_TITLE);
		if(rolSelected == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ROL_INICIALIZADO);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
		if(rolSelected.getNombre() == null || rolSelected.getNombre().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_OFICINA_NOMBRE);
			ctrlMessage.setComponentZk(rolNombreTxt);
			rolNombreTxt.setFocus(true);
			return ctrlMessage;
		}
		Rol localRol = rolRest.getByNombre(rolSelected.getNombre());
		if (localRol != null && localRol.getNombre().equals(rolSelected.getNombre())) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ROL_DUPLICADO);
			ctrlMessage.setComponentZk(rolNombreTxt);
			rolNombreTxt.setFocus(true);
			return ctrlMessage;
		}
		return null;
	}
}
