package com.came.control.web.vm;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Textbox;

import com.came.control.beans.ConfigureColor;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Configuracion;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.vm.metaclases.LoginMetaclas;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class LoginVM extends LoginMetaclas {
	private static final long serialVersionUID = -2184499179368861931L;
	
	public LoginVM() {
		modusPassword = "password";
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtOrg.setFocus(true);
		Clients.showNotification("Ingresar RFC de la organización.", Clients.NOTIFICATION_TYPE_INFO, txtOrg, null, 0);
	}

	@NotifyChange({ "user", "password" })
	@Command
	public void authenticateUser() {
		
//		String mensaje = "mensaje desde java code";
//		Clients.evalJavaScript("closeBrowser('" + mensaje+ "')");

		MessageControl ctrlMessage = validarUsuario();
		if (ctrlMessage.getUsuario() != null) {
			Configuracion cfg = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_COLOR_THEME, ctrlMessage.getUsuario().getOrganizacion());
			ConfigureColor color = new Gson().fromJson(cfg.getValor(), ConfigureColor.class);
			
			ctrlUtilsSession.addToSession(ConstAtributos.SESSION_USUARIO, ctrlMessage.getUsuario());
			ctrlUtilsSession.addToSession(ConstAtributos.SESSION_FIRMA, ctrlMessage.getUsuario().getOrganizacion());
			ctrlUtilsSession.addToSession(ConstAtributos.SESSION_COLOR_CONFIGURE, color);
			ctrlUtils.redirect("/home.zul");
		} else {
			Clients.showNotification(ctrlMessage.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, ctrlMessage.getComponentZk(), null, 0);
			Textbox txt = (Textbox) ctrlMessage.getComponentZk();
			txt.setFocus(true);
			return;
		}
	}
	
	@NotifyChange({ "modusPassword"})
	@Command
	public void showContrasenia(){
		if(verPassword)
			modusPassword = "text";
		else
			modusPassword = "password";
	}
}
