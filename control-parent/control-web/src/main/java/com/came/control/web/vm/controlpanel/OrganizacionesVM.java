package com.came.control.web.vm.controlpanel;

import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.model.beans.RefactoringJs;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class OrganizacionesVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;

	@Wire("#organizacionesZul, #orgfWinForm, #orgNombreTxt")
	private Textbox orgNombreTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirCalleTxt")
	private Textbox orgDirCalleTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirNoIntTxt")
	private Textbox orgDirNoIntTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirNoExtTxt")
	private Textbox orgDirNoExtTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirColoniaTxt")
	private Textbox orgDirColoniaTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirCpTxt")
	private Textbox orgDirCpTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirEstadoComboBox")
	private Combobox orgDirEstadoComboBox;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirMunComboBox")
	private Combobox orgDirMunComboBox;
	@Wire("#organizacionesZul, #orgfWinForm, #orgDirtelTxt")
	private Textbox orgDirtelTxt;
	
	@Wire("#organizacionesZul, #orgfWinForm, #orgRfcTxt")
	private Textbox orgRfcTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgEmailTxt")
	private Textbox orgEmailTxt;
	@Wire("#organizacionesZul, #orgfWinForm, #orgWebPageTxt")
	private Textbox orgWebPageTxt;
	
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		estados = (List<Estado>) estadoRest.getAll().getSingle();
		initProperties();
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		orgNombreTxt.setFocus(true);
	}
	
	@Command
	public void listenerLocation() {
		System.err.println("Mapa Oficina");
	}
	
	@Command
	@NotifyChange({"organizacionSelected", "estados", "municipios"})
	public void pushBindDataGeoSelect(){
		Geolocalizacion geo = new Gson().fromJson(mapTransferTxt.getText(), Geolocalizacion.class);
		mapTransferTxt.setText("");
		
		if(organizacionSelected.getGeolocalizacion() == null || organizacionSelected.getGeolocalizacion().getIdGeolocalizacion() == null)
			organizacionSelected.setGeolocalizacion(geo);
		
		RefactoringJs ref = refactorarJsInfoDetected(organizacionSelected.getDireccion(),
				geo.getDir(), organizacionSelected.getGeolocalizacion(), geo);
		organizacionSelected.setDireccion(ref.getDireccion());
		organizacionSelected.setGeolocalizacion(ref.getGeolocalizacion());
		
//		if(organizacionSelected.getDireccion() == null || organizacionSelected.getDireccion().getIdDireccion() == null) {
//			if(geo.getDir().getMunicipio() != null)
//				geo.getDir().setMunicipio(iteratorList.getMunicipioById(municipios, geo.getDir().getMunicipio().getIdMunicipio()));
//			
//			if(geo.getDir().getCalle() == null || geo.getDir().getCalle().isEmpty())
//				geo.getDir().setCalle(organizacionSelected.getDireccion() != null ? organizacionSelected.getDireccion().getCalle() : null);
//			if(geo.getDir().getNumeroInterior() == null || geo.getDir().getNumeroInterior().isEmpty())
//				geo.getDir().setNumeroInterior(organizacionSelected.getDireccion() != null ? organizacionSelected.getDireccion().getNumeroInterior() : null);
//			if(geo.getDir().getNumeroExterior() == null || geo.getDir().getNumeroExterior().isEmpty())
//				geo.getDir().setNumeroExterior(organizacionSelected.getDireccion() != null ? organizacionSelected.getDireccion().getNumeroExterior() : null);
//			if(geo.getDir().getColonia() == null || geo.getDir().getColonia().isEmpty())
//				geo.getDir().setColonia(organizacionSelected.getDireccion() != null ? organizacionSelected.getDireccion().getColonia() : null);
//			if(geo.getDir().getCp() == null || geo.getDir().getCp().isEmpty())
//				geo.getDir().setCp(organizacionSelected.getDireccion() != null ? organizacionSelected.getDireccion().getCp() : null);
//			if(geo.getDir().getTelefono().getOficina() == null || geo.getDir().getTelefono().getOficina().isEmpty())
//				geo.getDir().getTelefono().setOficina(organizacionSelected.getDireccion().getTelefono().getOficina());
//			organizacionSelected.setDireccion(geo.getDir());
//		} else {
//			if(geo.getDir() != null) {
//				organizacionSelected.getDireccion().setCalle(geo.getDir().getCalle());
//				organizacionSelected.getDireccion().setColonia(geo.getDir().getColonia());
//				organizacionSelected.getDireccion().setCp(geo.getDir().getCp());
//				organizacionSelected.getDireccion().setEstado(iteratorList.getEstadoById(estados, geo.getDir().getEstado().getIdEstado()));
//				
//				municipios = (List<Municipio>) municipioRest.getByEstado(organizacionSelected.getDireccion().getEstado()).getSingle();
//				
//				organizacionSelected.getDireccion().setMunicipio(iteratorList.getMunicipioById(municipios, geo.getDir().getMunicipio().getIdMunicipio()));
//				organizacionSelected.getDireccion().setTelefono(geo.getDir().getTelefono());
//			} else {
//				municipios = (List<Municipio>) municipioRest.getByEstado(organizacionSelected.getDireccion().getEstado()).getSingle();
//				if(organizacionSelected.getDireccion().getMunicipio() != null) {
//					organizacionSelected.getDireccion().setMunicipio(iteratorList.getMunicipioById(municipios, geo.getDir().getMunicipio().getIdMunicipio()));
//				}
//			}
//			
//			organizacionSelected.getGeolocalizacion().setDescripcion(geo.getDescripcion());
//			organizacionSelected.getGeolocalizacion().setDirCalle(geo.getDirCalle());
//			organizacionSelected.getGeolocalizacion().setDirColonia(geo.getDirColonia());
//			organizacionSelected.getGeolocalizacion().setDirCP(geo.getDirCP());
//			organizacionSelected.getGeolocalizacion().setDirEstado(geo.getDirEstado());
//			organizacionSelected.getGeolocalizacion().setDirMunicipio(geo.getDirMunicipio());
//			organizacionSelected.getGeolocalizacion().setDirPais(geo.getDirPais());
//			organizacionSelected.getGeolocalizacion().setLatitud(geo.getLatitud());
//			organizacionSelected.getGeolocalizacion().setLongitud(geo.getLongitud());
//		}
			
			
	}
	
	@Command
	@NotifyChange({"organizacionSelected", "estados", "municipios"})
	public void clickedOrganizacionNueva() {
		initProperties();
	}
	
	@Command
	public void clickedOrganizacionGuardar() {
		MessageControl validador = validarFormulario();
		if(validador == null) {
			organizacionSelected.setGeolocalizacion((Geolocalizacion) geolocalizacionRest.save(organizacionSelected.getGeolocalizacion()).getSingle());
			organizacionSelected.getDireccion().setTelefono((Telefono) telefonoRest.save(organizacionSelected.getDireccion().getTelefono()).getSingle());
			organizacionSelected.setDireccion((Direccion) direccionRest.save(organizacionSelected.getDireccion()).getSingle());
			organizacionSelected = (Organizacion) organizacionRest.save(organizacionSelected).getSingle();

			String message = "La organizacion <b>" + organizacionSelected.getRazonSocial() + "</b> " + (organizacionSelected.getIdOrganizacion() == null ? " ha sido creado" : " ha sido modificada");
			Clients.showNotification(message, Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
	}
	
	@Command
	public void clickedOrganizacionBuscador() {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "showOrganizacionSelected");
		inputParams.put("org", organizacion);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/buscadorOrganizaciones.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	//@NotifyChange({"organizacionSelected", "municipios"})
	@NotifyChange({"*"})
	public void showOrganizacionSelected(
			@BindingParam("org") Organizacion ofic) {
		organizacionSelected = ofic;
		
		if(organizacionSelected.getDireccion() != null) {
			if(organizacionSelected.getDireccion().getEstado() != null) {
				organizacionSelected.getDireccion().setEstado(iteratorList.getEstadoById(estados, organizacionSelected.getDireccion().getEstado().getIdEstado()));
			
				if(organizacionSelected.getDireccion().getMunicipio() != null) {
					municipios = municipioRest.getByEstado(organizacionSelected.getDireccion().getEstado());
					organizacionSelected.getDireccion().setMunicipio(iteratorList.getMunicipioById(municipios, organizacionSelected.getDireccion().getMunicipio().getIdMunicipio()));
				}
			}
			
			
			
		}
		
		if(organizacionSelected.getGeolocalizacion() != null && (organizacionSelected.getGeolocalizacion().getDescripcion() == null || organizacionSelected.getGeolocalizacion().getDescripcion().isEmpty()) ) {
			String descripcion = "";
			if(organizacionSelected.getDireccion() != null) {
				if(organizacionSelected.getDireccion().getCalle() != null && !organizacionSelected.getDireccion().getCalle().isEmpty()) {
					descripcion = organizacionSelected.getDireccion().getCalle() + ", ";
				}
				if(organizacionSelected.getDireccion().getColonia() != null && !organizacionSelected.getDireccion().getColonia().isEmpty()) {
					descripcion += organizacionSelected.getDireccion().getColonia() + ", ";
				}
				
				if(organizacionSelected.getDireccion().getMunicipio() != null && !organizacionSelected.getDireccion().getMunicipio().getNombre().isEmpty()) {
					descripcion += organizacionSelected.getDireccion().getMunicipio().getNombre() + ", ";
				}
				
				if(organizacionSelected.getDireccion().getEstado() != null && !organizacionSelected.getDireccion().getEstado().getNombre().isEmpty()) {
					descripcion += organizacionSelected.getDireccion().getEstado().getNombre() + ", ";
				}
				
				if(organizacionSelected.getDireccion().getCp() != null && !organizacionSelected.getDireccion().getCp().isEmpty()) {
					descripcion += organizacionSelected.getDireccion().getCp() + ", ";
				}
				if(!descripcion.isEmpty())
					descripcion = descripcion.substring(0, (descripcion.length() - 2) );
				organizacionSelected.getGeolocalizacion().setDescripcion(descripcion);
			}
				 
			
		}
		Clients.evalJavaScript("selectedCityJs('" + new Gson().toJson(organizacionSelected.getGeolocalizacion())+ "')");
	}
	
	@Command
	@NotifyChange({"organizacionSelected", "municipios"})
	public void clickedEstado() {
		municipios = municipioRest.getByEstado(organizacionSelected.getDireccion().getEstado());
		organizacionSelected.getDireccion().setMunicipio(null);
	}
	
	
	
	private void initProperties() {
		organizacionSelected = new Organizacion();
		Direccion dir = new Direccion();
		
		dir.setTelefono(new Telefono());
		organizacionSelected.setDireccion(dir);
		
		selectedPais = iteratorList.getPaisByClave(paises, ConstAtributos.MX);
		
		organizacionSelected.setGeolocalizacion(new Geolocalizacion());
		if(geoLat != null)
			geoLat.setValue(0D);
		if(geoLong != null)
			geoLong.setValue(0D);
		if(mapTransferTxt != null)
			mapTransferTxt.setText("");
		
		municipios = null;
	}
	
	private MessageControl validarFormulario(){
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_ORGANIZACION_TITLE);
		if(organizacionSelected == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ORGANIZACION_INICIALIZADO);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getRazonSocial() == null || organizacionSelected.getRazonSocial().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ORGANIZACION_RAZON_SOCIAL);
			ctrlMessage.setComponentZk(orgNombreTxt);
			orgNombreTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getDireccion().getNumeroExterior() == null || organizacionSelected.getDireccion().getNumeroExterior().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_NUM_EXT);
			ctrlMessage.setComponentZk(orgDirNoExtTxt);
			orgDirNoExtTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getDireccion().getEstado() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_EDO);
			ctrlMessage.setComponentZk(orgDirEstadoComboBox);
			orgDirEstadoComboBox.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getDireccion().getMunicipio() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_MUNICIPIO);
			ctrlMessage.setComponentZk(orgDirMunComboBox);
			orgDirMunComboBox.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getDireccion().getTelefono() == null)
			organizacionSelected.getDireccion().setTelefono(new Telefono());
		if(organizacionSelected.getDireccion().getTelefono().getOficina() == null || organizacionSelected.getDireccion().getTelefono().getOficina().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_TELEFONO);
			ctrlMessage.setComponentZk(orgDirtelTxt);
			orgDirtelTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getDireccion().getTelefono().getOficina() == null || organizacionSelected.getDireccion().getTelefono().getOficina().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_RFC);
			ctrlMessage.setComponentZk(orgRfcTxt);
			orgRfcTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(organizacionSelected.getGeolocalizacion() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_GEOLOCALIZACION);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
//		organizacionSelected.getGeolocalizacion().setLatitud(geoLat.getValue() != null && !geoLat.getValue().isEmpty() ? geoLat.getValue() : null);
//		organizacionSelected.getGeolocalizacion().setLongitud(geoLong.getValue() != null && !geoLong.getValue().isEmpty() ? geoLong.getValue() : null);
		organizacionSelected.getGeolocalizacion().setLatitud(geoLat.getValue() != null ? geoLat.getValue().toString() : null);
		organizacionSelected.getGeolocalizacion().setLongitud(geoLong.getValue() != null ? geoLong.getValue().toString() : null);
		
		if((organizacionSelected.getGeolocalizacion().getLatitud() == null || organizacionSelected.getGeolocalizacion().getLatitud().isEmpty()) || (organizacionSelected.getGeolocalizacion().getLongitud() == null || organizacionSelected.getGeolocalizacion().getLongitud().isEmpty())) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_COORDENADAS);
			ctrlMessage.setComponentZk(geoLat);
			geoLat.setFocus(true);
			return ctrlMessage;
		}
		return null;
	}
}
