package com.came.control.web.vm;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import com.came.control.beans.ConfigureColor;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.vm.metaclases.HomeMetaclas;

@VariableResolver({ DelegatingVariableResolver.class })
public class HomeVM extends HomeMetaclas {

	private static final long serialVersionUID = 8527729558603183958L;

//	@Wire("#homeWindow, #blMain, #nortMenu, #menuBar")
//	private Menubar menubar;
	
	
	
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		colorTheme = ((ConfigureColor) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_COLOR_CONFIGURE));
		usuario.setImagenContent(usuario.getImagen() != null ? ctrlUtils.pathImagenToAimage(usuario.getImagen()) : null);
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({ "*" })
	@GlobalCommand
	public void updateWorkArea(@BindingParam("pageToRender") String pageToRender) {
		List<Component> components = this.content.getChildren();
		if (components != null) {
			components.clear();
			this.content.appendChild(new Include(pageToRender));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void logOut() {
		Messagebox.show("¿Desea terminar su session de trabajo?",
				"Salir del sistema", 3, "z-msgbox z-msgbox-question",
				new EventListener() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == 1) {
							HomeVM.this.ctrlUtilsSession.logOut();
							HomeVM.this.ctrlUtils.redirect("/login.zul");
						}
					}
				});
	}

	
	@NotifyChange({ "moduloSession" })
	@GlobalCommand
	public void refresNameModule(){
//		moduloSession = ((ModuloUsuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
//		System.err.println("mensaje global: " + moduloSession.getModulo().getNombre());
	}
	
}
