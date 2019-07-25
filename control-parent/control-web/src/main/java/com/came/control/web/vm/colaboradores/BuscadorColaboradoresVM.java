package com.came.control.web.vm.colaboradores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class BuscadorColaboradoresVM extends LayerWebOperaciones {

	private static final long serialVersionUID = 5170566493846834579L;

	@Wire("#buscadorColaboradoresMD")
	private Window buscadorColaboradoresMD;

	private String globalCommandName;

	private List<Usuario> modalUsuarios;
	private Usuario modalColaborador;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("org") Organizacion orgIn) {
		globalCommandName = itemFinder;
		modalColaborador = new Usuario();
		modalUsuarios = usuarioRest.getByOrganizacion(orgIn);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

	}

	@Command
	public void transfer() {
		buscadorColaboradoresMD.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("usr", modalColaborador);
		if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty()))
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		else
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);

	}

	@Command
	public void closeDialog() {
		if (buscadorColaboradoresMD != null) {
			buscadorColaboradoresMD.detach();
		}
	}

	public List<Usuario> getModalUsuarios() {
		return modalUsuarios;
	}

	public void setModalUsuarios(List<Usuario> modalUsuarios) {
		this.modalUsuarios = modalUsuarios;
	}

	public Usuario getModalColaborador() {
		return modalColaborador;
	}

	public void setModalColaborador(Usuario modalColaborador) {
		this.modalColaborador = modalColaborador;
	}

}
