package com.came.control.web.vm.controlpanel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class MapMakerSelectedVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;

	@Init
	public void init() {
		
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void listenerLocation() {
		System.err.println("Mapa Oficina");
	}
}
