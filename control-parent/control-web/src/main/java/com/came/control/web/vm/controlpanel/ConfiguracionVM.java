package com.came.control.web.vm.controlpanel;

import java.util.Calendar;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.control.beans.ConfigureAccuracy;
import com.came.control.beans.ConfigureHabilitarFuncion;
import com.came.control.beans.ConfigureTimer;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class ConfiguracionVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;

	private Configuracion cfgAccuracy;
	private Configuracion cfgHabilita;
	private Configuracion cfgTimer;
	private ConfigureAccuracy configureAccuracy;
	private ConfigureHabilitarFuncion configureHabilitarFuncion;
	private ConfigureTimer configureTimer;
	private Gson gson;

	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		
		gson = new Gson();
		cfgAccuracy = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_ACCURACY, organizacion);
		if(cfgAccuracy != null)
			configureAccuracy = gson.fromJson(cfgAccuracy.getValor(), ConfigureAccuracy.class);
		cfgHabilita = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_MSG_INCIDENCIAS, organizacion);
		if(cfgHabilita != null)
			configureHabilitarFuncion = gson.fromJson(cfgHabilita.getValor(), ConfigureHabilitarFuncion.class);
		cfgTimer = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_TIME_INCIDENCIAS, organizacion);
		if(cfgTimer != null) {
			configureTimer = gson.fromJson(cfgTimer.getValor(), ConfigureTimer.class);
			String [] splitter = configureTimer.getHora().replace(":", "-").split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitter[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(splitter[1]));
			configureTimer.setCaptureValue(cal.getTime());
		}
			
	}
	
	@Command
	public void guardarCambios() {
		String validador = validador();
		if(validador == null) {
			cfgAccuracy.setValor(gson.toJson(configureAccuracy));
			cfgAccuracy = configuracionRest.save(cfgAccuracy);
			
			cfgHabilita.setValor(gson.toJson(configureHabilitarFuncion));
			cfgHabilita = configuracionRest.save(cfgHabilita);
			
			cfgTimer.setValor(gson.toJson(configureTimer));
			cfgTimer = configuracionRest.save(cfgTimer);
			
			Clients.showNotification("<b>Cambios actualizados</b>", Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		} else {
			Clients.showNotification(validador, Clients.NOTIFICATION_TYPE_WARNING, null, null, 0);
		}
	}
	
	private String validador() {
		if(configureAccuracy.getMetros() == null || configureAccuracy.getMetros() < 1) {
			return "Unidad de metros <b>inválido</b> para el radio accuracy";
		}
		if(configureTimer.getCaptureValue() == null) {
			return "Hora <b>inválida</b> para el corte de incidencias";
		} 
		return null;
	}

	public ConfigureAccuracy getConfigureAccuracy() {
		return configureAccuracy;
	}

	public void setConfigureAccuracy(ConfigureAccuracy configureAccuracy) {
		this.configureAccuracy = configureAccuracy;
	}

	public ConfigureHabilitarFuncion getConfigureHabilitarFuncion() {
		return configureHabilitarFuncion;
	}

	public void setConfigureHabilitarFuncion(ConfigureHabilitarFuncion configureHabilitarFuncion) {
		this.configureHabilitarFuncion = configureHabilitarFuncion;
	}

	public ConfigureTimer getConfigureTimer() {
		return configureTimer;
	}

	public void setConfigureTimer(ConfigureTimer configureTimer) {
		this.configureTimer = configureTimer;
	}

}
