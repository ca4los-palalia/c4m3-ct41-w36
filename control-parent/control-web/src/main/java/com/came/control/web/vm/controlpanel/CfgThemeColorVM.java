package com.came.control.web.vm.controlpanel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Slider;
import org.zkoss.zul.Textbox;

import com.came.control.beans.ConfigureColor;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.layer.LayerWebOperaciones;
import com.came.control.web.vm.HomeVM;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class CfgThemeColorVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;
	
	@Wire("#configuracionzul, #firedUp")
	private Textbox transfereTxt;
	@Wire("#configuracionzul, #sliderColor1")
	private Slider sliderColor1;
	@Wire("#configuracionzul, #sliderColor2")
	private Slider sliderColor2;
	
	
//	private ColorConfigure color;
	private String orientacion1;
	private String orientacion2;
	private String orientacion3;
	private String orientacion4;
	private String orientacion5;
	private String orientacion6;
	private String orientacion7;
	private String orientacion8;
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		colorTheme = ((ConfigureColor) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_COLOR_CONFIGURE));
		
		sliderColor1.setCurpos(colorTheme.getColor1Posicion());
		sliderColor2.setCurpos(colorTheme.getColor2Posicion());
		
		updateOrientacion();
		Clients.evalJavaScript("inicializarThemeColor('" + new Gson().toJson(colorTheme)+ "')");
		
	}
	
	@Command
	@NotifyChange({"colorTheme", "orientacion1", "orientacion2", "orientacion3", "orientacion4", "orientacion5", "orientacion6", "orientacion7", "orientacion8"})
	public void updateColorChooser1() {
		ConfigureColor recuperador = new Gson().fromJson(transfereTxt.getValue(), ConfigureColor.class);
		colorTheme = recuperador;
		updateOrientacion();
		transfereTxt.setValue(null);
	}
	
	@Command
	@NotifyChange({"colorTheme", "orientacion1", "orientacion2", "orientacion3", "orientacion4", "orientacion5", "orientacion6", "orientacion7", "orientacion8"})
	public void updateColorChooser2() {
		ConfigureColor recuperador = new Gson().fromJson(transfereTxt.getValue(), ConfigureColor.class);
		colorTheme = recuperador;
		updateOrientacion();
		transfereTxt.setValue(null);
	}
	
	@Command
	@NotifyChange({"colorTheme", "orientacion1", "orientacion2", "orientacion3", "orientacion4", "orientacion5", "orientacion6", "orientacion7", "orientacion8"})
	public void changeC1Position() {
		colorTheme.setColor1Posicion(sliderColor1.getCurpos());
		colorTheme.setColorDegradado(updateStyleTag());
		updateOrientacion();
		
	}
	
	@Command
	@NotifyChange({"colorTheme", "orientacion1", "orientacion2", "orientacion3", "orientacion4", "orientacion5", "orientacion6", "orientacion7", "orientacion8"})
	public void changeC2Position() {
		colorTheme.setColor2Posicion(sliderColor2.getCurpos());
		colorTheme.setColorDegradado(updateStyleTag());
		updateOrientacion();
	}
	
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion1() {
		colorTheme.setColorDegradado(orientacion1);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion2() {
		colorTheme.setColorDegradado(orientacion2);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion3() {
		colorTheme.setColorDegradado(orientacion3);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion4() {
		colorTheme.setColorDegradado(orientacion4);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion5() {
		colorTheme.setColorDegradado(orientacion5);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion6() {
		colorTheme.setColorDegradado(orientacion6);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion7() {
		colorTheme.setColorDegradado(orientacion7);
	}
	
	@Command
	@NotifyChange({"colorTheme"})
	public void orientacion8() {
		colorTheme.setColorDegradado(orientacion8);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({"colorTheme"})
	public void save() {
		Messagebox.show("¿Desea aplicar los cambios al sistema?",
			"Configuracion del sistema", 3, "z-msgbox z-msgbox-question",
			new EventListener() {
				public void onEvent(Event event) throws Exception {
					if (((Integer) event.getData()).intValue() == 1) {
						Configuracion cfg = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_COLOR_THEME, organizacion);
						cfg.setValor(new Gson().toJson(colorTheme));
						configuracionRest.save(cfg);
						
						ctrlUtilsSession.addToSession(ConstAtributos.SESSION_COLOR_CONFIGURE, colorTheme);
						CfgThemeColorVM.this.ctrlUtils.redirect("/home.zul");
						Clients.showNotification("<b>Color actualizado</b>", Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
					}
				}
			});
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({"colorTheme"})
	public void defaultTheme() {
		Messagebox.show("¿Desea aplicar los cambios al sistema?",
			"Configuracion del sistema", 3, "z-msgbox z-msgbox-question",
			new EventListener() {
				public void onEvent(Event event) throws Exception {
					if (((Integer) event.getData()).intValue() == 1) {
						Configuracion cfg = configuracionRest.getByClaveAndOrg(ConstAtributos.CFG_COLOR_THEME, organizacion);
						Configuracion cfgDefault = iteratorList.crearColorThemeDefault(organizacion);
						ConfigureColor colorDefault = new Gson().fromJson(cfgDefault.getValor(), ConfigureColor.class);
						
						cfg.setValor(new Gson().toJson(colorDefault));
						
						configuracionRest.save(cfg);
						colorTheme = colorDefault;
						
						ctrlUtilsSession.addToSession(ConstAtributos.SESSION_COLOR_CONFIGURE, colorTheme);
						CfgThemeColorVM.this.ctrlUtils.redirect("/home.zul");
						Clients.showNotification("<b>Color actualizado</b>", Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
					}
				}
			});
	}
	
	private String updateStyleTag() {
		return "background: linear-gradient(" + colorTheme.getOrientacion() + ", " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
	}
	
	private void updateOrientacion() {
		orientacion1 = "background: linear-gradient(to bottom, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion2 = "background: linear-gradient(to right, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion3 = "background: linear-gradient(to top left, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion4 = "background: linear-gradient(to top right, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion5 = "background: linear-gradient(to top, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion6 = "background: linear-gradient(to left, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion7 = "background: linear-gradient(to bottom left, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
		orientacion8 = "background: linear-gradient(to bottom right, " + colorTheme.getColor1() + " " + colorTheme.getColor1Posicion() + "%, " + colorTheme.getColor2() + " " + colorTheme.getColor2Posicion() + "%);";
	}

	public String getOrientacion1() {
		return orientacion1;
	}

	public void setOrientacion1(String orientacion1) {
		this.orientacion1 = orientacion1;
	}

	public String getOrientacion2() {
		return orientacion2;
	}

	public void setOrientacion2(String orientacion2) {
		this.orientacion2 = orientacion2;
	}

	public String getOrientacion3() {
		return orientacion3;
	}

	public void setOrientacion3(String orientacion3) {
		this.orientacion3 = orientacion3;
	}

	public String getOrientacion4() {
		return orientacion4;
	}

	public void setOrientacion4(String orientacion4) {
		this.orientacion4 = orientacion4;
	}

	public String getOrientacion5() {
		return orientacion5;
	}

	public void setOrientacion5(String orientacion5) {
		this.orientacion5 = orientacion5;
	}

	public String getOrientacion6() {
		return orientacion6;
	}

	public void setOrientacion6(String orientacion6) {
		this.orientacion6 = orientacion6;
	}

	public String getOrientacion7() {
		return orientacion7;
	}

	public void setOrientacion7(String orientacion7) {
		this.orientacion7 = orientacion7;
	}

	public String getOrientacion8() {
		return orientacion8;
	}

	public void setOrientacion8(String orientacion8) {
		this.orientacion8 = orientacion8;
	}
}
