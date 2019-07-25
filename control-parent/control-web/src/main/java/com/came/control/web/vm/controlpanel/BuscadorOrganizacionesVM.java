package com.came.control.web.vm.controlpanel;

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
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class BuscadorOrganizacionesVM extends LayerWebOperaciones {

	private static final long serialVersionUID = 5170566493846834579L;

	@Wire("#buscadorOrgMD")
	private Window buscadorOrgMD;

	private String globalCommandName;

	private List<Organizacion> listaOrganizaciones;
	private Organizacion organizacionSelected;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("org") Organizacion orgIn) {
		globalCommandName = itemFinder;
		organizacionSelected = new Organizacion();
		listaOrganizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

	}

	@Command
	public void transfer() {
		buscadorOrgMD.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("org", organizacionSelected);
		if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty()))
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		else
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);

	}

	@Command
	public void closeDialog() {
		if (buscadorOrgMD != null) {
			buscadorOrgMD.detach();
		}
	}

	public List<Organizacion> getListaOrganizaciones() {
		return listaOrganizaciones;
	}

	public void setListaOrganizaciones(List<Organizacion> listaOrganizaciones) {
		this.listaOrganizaciones = listaOrganizaciones;
	}

	public Organizacion getOrganizacionSelected() {
		return organizacionSelected;
	}

	public void setOrganizacionSelected(Organizacion organizacionSelected) {
		this.organizacionSelected = organizacionSelected;
	}

	

}
