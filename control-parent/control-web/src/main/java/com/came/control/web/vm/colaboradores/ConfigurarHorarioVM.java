package com.came.control.web.vm.colaboradores;

import java.util.Calendar;
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
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.came.control.beans.TimeBoxObject;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class ConfigurarHorarioVM extends LayerWebOperaciones {

	private static final long serialVersionUID = 5170566493846834579L;

	@Wire("#configurarHorarioZul")
	private Window configurarHorarioZul;
	@Wire("#configurarHorarioZul, #cmbxZonaHorarios")
	private Combobox cmbxZonaHorarios;
	@Wire("#configurarHorarioZul, #btnCancel")
	private Button btnCancel;
	@Wire("#configurarHorarioZul, #btnOk")
	private Button btnOk;
	@Wire("#configurarHorarioZul, #transfer")
	private Textbox transfer;
	
	@Wire("#configurarHorarioZul, #transferHorario")
	protected Textbox transferHorario;

	private String globalCommandName;
	private ZonaHorario selectedZonaHorario;
	private List<Horario> listaHorarios;
	
	private boolean checkAllInJornada;
	private boolean checkAllOutJornada;
	private boolean checkAllInComida;
	private boolean checkAllOutComida;
	
	private String titleTag;
	private boolean readOnly;
	private boolean buttonVisible;
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("selectedUsr") Usuario selectedUsrIn,
			@ExecutionArgParam("readOnly") boolean readOnlyIn,
			@ExecutionArgParam("buttonVisible") boolean buttonVisibleIn,
			@ExecutionArgParam("org") Organizacion org) {
		readOnly = readOnlyIn;
		buttonVisible = buttonVisibleIn;
		globalCommandName = itemFinder;
//		selectedOficina = selectedUsrIn;
		
		zonasHorarios = zonaHorarioRest.getByOrganizacion(org);
		
		
		if(selectedUsrIn != null && selectedUsrIn.getIdUsuario() != null) {
			listaHorarios = horarioRest.getByUsuario(selectedUsrIn);
			selectedZonaHorario = listaHorarios.get(0).getZonaHorario();
			if(selectedZonaHorario != null)
				selectedZonaHorario = iteratorList.getZonaHorarioById(zonasHorarios, selectedZonaHorario.getIdZonaHorario());
			titleTag = "Actualizar horario del usuario: " + selectedUsrIn.getDatosGenerales().getPersona().getNombreCompleto();
		}
			
		if(listaHorarios == null) {
			if(selectedUsrIn.getHorarios() == null) {
				listaHorarios = iteratorList.crearHorarioTemporal(null, selectedZonaHorario);
				selectedZonaHorario = iteratorList.getZonaHorarioByZh(zonasHorarios, ConstAtributos.ZH_MEXICO_CITY);
				titleTag = "Asignar horario para el nuevo usuario";
			} else {
				listaHorarios = selectedUsrIn.getHorarios();
				selectedZonaHorario = listaHorarios.get(0).getZonaHorario();
				if(selectedZonaHorario != null)
					selectedZonaHorario = iteratorList.getZonaHorarioById(zonasHorarios, selectedZonaHorario.getIdZonaHorario());
				titleTag = "Continuar asignando horario para el nuevo usuario";
			}
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

	}

	@Command
	public void transfer() {
		if(selectedZonaHorario == null) {
			Clients.showNotification("Debe seleccionar la Zona horaria.", Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
			return;
		}
		
		for (Horario item : listaHorarios)
			item.setZonaHorario(selectedZonaHorario);
		
		configurarHorarioZul.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("lista", listaHorarios);
		if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty()))
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		else
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);

	}

	@Command
	public void closeDialog() {
		if (configurarHorarioZul != null) {
			configurarHorarioZul.detach();
		}
	}
	
	@Command
	@NotifyChange({"listaHorarios"})
	public void jornadaEntradaTimer() {
		TimeBoxObject data = new Gson().fromJson(transferHorario.getValue(), TimeBoxObject.class);
		transferHorario.setValue(null);
		for (int i = 0; i < listaHorarios.size(); i++) {
			Horario item = listaHorarios.get(i);
			Integer idIndex = Integer.parseInt(data.getIdIndex());
			//Columna 1
			if(idIndex < 10) {
				item.sethEntradaDate(builderCalendarFromTime(data.getTime(), Calendar.getInstance()).getTime());
			}
			//Columna 2
			else if(idIndex >= 10 && idIndex < 20) {
				item.sethSalidaDate(builderCalendarFromTime(data.getTime(), Calendar.getInstance()).getTime());
			}
			//Columna 3
			else if(idIndex >= 20 && idIndex < 30) {
				item.sethComidaEntradaDate(builderCalendarFromTime(data.getTime(), Calendar.getInstance()).getTime());
			}
			//Columna 4
			else if(idIndex >= 30) {
				item.sethComidaSalidaDate(builderCalendarFromTime(data.getTime(), Calendar.getInstance()).getTime());
			}
		}
	}

	public List<Horario> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public boolean isCheckAllInJornada() {
		return checkAllInJornada;
	}

	public void setCheckAllInJornada(boolean checkAllInJornada) {
		this.checkAllInJornada = checkAllInJornada;
	}

	public boolean isCheckAllOutJornada() {
		return checkAllOutJornada;
	}

	public void setCheckAllOutJornada(boolean checkAllOutJornada) {
		this.checkAllOutJornada = checkAllOutJornada;
	}

	public boolean isCheckAllInComida() {
		return checkAllInComida;
	}

	public void setCheckAllInComida(boolean checkAllInComida) {
		this.checkAllInComida = checkAllInComida;
	}

	public boolean isCheckAllOutComida() {
		return checkAllOutComida;
	}

	public void setCheckAllOutComida(boolean checkAllOutComida) {
		this.checkAllOutComida = checkAllOutComida;
	}

	public ZonaHorario getSelectedZonaHorario() {
		return selectedZonaHorario;
	}

	public void setSelectedZonaHorario(ZonaHorario selectedZonaHorario) {
		this.selectedZonaHorario = selectedZonaHorario;
	}

	public String getTitleTag() {
		return titleTag;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isButtonVisible() {
		return buttonVisible;
	}
}
