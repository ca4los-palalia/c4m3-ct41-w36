package com.came.control.web.vm.controlpanel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class AsistenciaVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;
	
	@Wire("#zhZul, #asistenciasListbox")
	private Listbox asistenciasListbox;
	
	private Date fechaInicio;
	private Date fechaFin;
	
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		//asistencias = asistenciaRest.getByOrganizacion(organizacion);
		initProperties();
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	private void initProperties() {
		selectedAsistencia = new Asistencia();
		selectedAsistencia.setOrganizacion(organizacion);
		fechaFin = new Date();
		
		asistencias = asistenciaRest.getByOrganizacion(organizacion, fechaFin);
		fechaInicio = ctrlUtils.getFechaControladaCalendar(ctrlUtils.convertirDateToCalendar(fechaFin), true).getTime();
	}
	
	@Command
	@NotifyChange({"usuarioIncidencias", "selectedUsuarioIncidencia"})
	public void showIncidencias(){
		usuarioIncidencias = usuarioIncidenciaRest.getByUsuarioAndFecha(selectedAsistencia.getUsuario(),
				selectedAsistencia.getOrganizacion(), ctrlUtils.convertirStringToCalendarWithFormat(selectedAsistencia.getFechaActualizacion(), ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS).getTime());
	}
	
	@Command
	public void agregarIncidenciaManaul(){
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "incidenciaManualAgregada");
		inputParams.put("org", organizacion);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/agregaIncidenciaManual.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	public void incidenciaManualAgregada(
			@BindingParam("incidencia") Incidencia item) {
		
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
