package com.came.control.web.vm.controlpanel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogosVM extends LayerWebOperaciones {
	
	private static final long serialVersionUID = 313977001812349337L;
	

	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
	}

	
}
