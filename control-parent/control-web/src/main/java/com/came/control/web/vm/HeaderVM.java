package com.came.control.web.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.vm.metaclases.HeaderMetaclas;

@VariableResolver({ DelegatingVariableResolver.class })
public class HeaderVM extends HeaderMetaclas {

	private static final long serialVersionUID = -1635442587326363484L;

	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
	}

	@Command
	public void logOut() {
		Messagebox.show("?Desea terminar su session de trabajo?", "Salir del sistema", 3, "z-msgbox z-msgbox-question",
				new EventListener() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == 1) {
							ctrlUtilsSession.logOut();
							ctrlUtils.redirect("/login.zul");
						}
					}
				});
	}

}
