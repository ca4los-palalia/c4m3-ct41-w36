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
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;
import com.google.gson.Gson;

@VariableResolver({ DelegatingVariableResolver.class })
public class OficinaVM extends LayerWebOperaciones {

	private static final long serialVersionUID = -1080292943153018368L;

//	@Wire("#oficinasZul, #offTransferTxt")
//	private Textbox textTransfer;
	@Wire("#oficinasZul, #offWinForm, #ofNombreTxt")
	private Textbox ofNombreTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirCalleTxt")
	private Textbox ofDirCalleTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirNoIntTxt")
	private Textbox ofDirNoIntTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirNoExtTxt")
	private Textbox ofDirNoExtTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirColoniaTxt")
	private Textbox ofDirColoniaTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirCpTxt")
	private Textbox ofDirCpTxt;
	@Wire("#oficinasZul, #offWinForm, #ofDirEstadoComboBox")
	private Combobox ofDirEstadoComboBox;
	@Wire("#oficinasZul, #offWinForm, #ofDirMunComboBox")
	private Combobox ofDirMunComboBox;
	@Wire("#oficinasZul, #offWinForm, #ofDirtelTxt")
	private Textbox ofDirtelTxt;
	
	
	
//	@Wire("#oficinasZul, #offWinForm, #finderAddress")
//	private Textbox finderAddress;
//	@Wire("#oficinasZul, #offWinForm, #geoLat")
//	private Textbox geoLat;
//	@Wire("#oficinasZul, #offWinForm, #geoLong")
//	private Textbox geoLong;
	
	
	
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));
		estados = (List<Estado>)estadoRest.getAll().getSingle();
		
		initProperties();
		
		
	}
	
	@NotifyChange({"*"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		ofNombreTxt.setFocus(true);
		
	}
	
	@Command
	public void listenerLocation() {
		System.err.println("Mapa Oficina");
	}
	
	@Command
	@NotifyChange({"selectedOficina", "estados", "municipios"})
	public void pushBindDataGeoSelect(){
		Geolocalizacion geo = new Gson().fromJson(mapTransferTxt.getText(), Geolocalizacion.class);
		mapTransferTxt.setText("");
		
		if(selectedOficina.getGeolocalizacion() == null || selectedOficina.getGeolocalizacion().getIdGeolocalizacion() == null)
			selectedOficina.setGeolocalizacion(geo);
		
		RefactoringJs ref = refactorarJsInfoDetected(selectedOficina.getDireccion(),
				geo.getDir(), selectedOficina.getGeolocalizacion(), geo);
		selectedOficina.setDireccion(ref.getDireccion());
		selectedOficina.setGeolocalizacion(ref.getGeolocalizacion());
		
//		if(selectedOficina.getDireccion() == null || selectedOficina.getDireccion().getIdDireccion() == null) {
//			if(geo.getDir().getMunicipio() != null)
//				geo.getDir().setMunicipio(iteratorList.getMunicipioById(municipios, geo.getDir().getMunicipio().getIdMunicipio()));
//			
//			if(geo.getDir().getCalle() == null || geo.getDir().getCalle().isEmpty())
//				geo.getDir().setCalle(selectedOficina.getDireccion() != null ? selectedOficina.getDireccion().getCalle() : null);
//			if(geo.getDir().getNumeroInterior() == null || geo.getDir().getNumeroInterior().isEmpty())
//				geo.getDir().setNumeroInterior(selectedOficina.getDireccion() != null ? selectedOficina.getDireccion().getNumeroInterior() : null);
//			if(geo.getDir().getNumeroExterior() == null || geo.getDir().getNumeroExterior().isEmpty())
//				geo.getDir().setNumeroExterior(selectedOficina.getDireccion() != null ? selectedOficina.getDireccion().getNumeroExterior() : null);
//			if(geo.getDir().getColonia() == null || geo.getDir().getColonia().isEmpty())
//				geo.getDir().setColonia(selectedOficina.getDireccion() != null ? selectedOficina.getDireccion().getColonia() : null);
//			if(geo.getDir().getCp() == null || geo.getDir().getCp().isEmpty())
//				geo.getDir().setCp(selectedOficina.getDireccion() != null ? selectedOficina.getDireccion().getCp() : null);
//			if(geo.getDir().getTelefono().getOficina() == null || geo.getDir().getTelefono().getOficina().isEmpty())
//				geo.getDir().getTelefono().setOficina(selectedOficina.getDireccion().getTelefono().getOficina());
//			selectedOficina.setDireccion(geo.getDir());
//		}
			
			
	}
	
	@Command
	public void configurarHorario() {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "configurarHorarioSelected");
		inputParams.put("selectedOficina", selectedOficina);
		inputParams.put("readOnly", false);
		inputParams.put("buttonVisible", true);
		
		inputParams.put("org", organizacion);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/configurarHorario.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	@NotifyChange({"selectedOficina"})
	public void configurarHorarioSelected(
			@BindingParam("lista") List<Horario> itemSelected) {
//		selectedOficina.setHorarios(itemSelected);
//		Clients.showNotification( ("Ha sido asignado un horario a la oficina <b>" + selectedOficina.getNombre() + "</b>"), Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		Clients.showNotification( ("<b>FUNCION UNACCESIBLE</b>"), Clients.NOTIFICATION_TYPE_ERROR, null, null, 0);
	}
	
	
	@Command
	@NotifyChange({"selectedOficina", "estados", "municipios"})
	public void clickedOficinaNueva() {
		initProperties();
	}
	
	@Command
	public void clickedOficinaGuardar() {
		MessageControl validador = validarFormulario();
		if(validador == null) {
			boolean nuevo = selectedOficina.getIdOficina() == null ? true : false;
			selectedOficina.setGeolocalizacion((Geolocalizacion) geolocalizacionRest.save(selectedOficina.getGeolocalizacion()).getSingle());
			selectedOficina.getDireccion().setTelefono((Telefono) telefonoRest.save(selectedOficina.getDireccion().getTelefono()).getSingle());
			selectedOficina.setDireccion((Direccion) direccionRest.save(selectedOficina.getDireccion()).getSingle());
			
			selectedOficina = (Oficina) oficinaRest.save(selectedOficina).getSingle();
			
			String message = "La oficina <b>" + selectedOficina.getNombre() +  "</b> ha sido " + (nuevo ? "creada" : "modificada");
			Clients.showNotification(message, Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
		} else
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
	}
	
	@Command
	public void clickedOficinaBuscador() {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "showOficinaSelected");
		inputParams.put("org", organizacion);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/buscadorOficinas.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	//@NotifyChange({"selectedOficina", "municipios"})
	@NotifyChange({"*"})
	public void showOficinaSelected(
			@BindingParam("oficina") Oficina ofic) {
		selectedOficina = ofic;
		
		if(selectedOficina.getDireccion() != null) {
			if(selectedOficina.getDireccion().getEstado() != null) {
				selectedOficina.getDireccion().setEstado(iteratorList.getEstadoById(estados, selectedOficina.getDireccion().getEstado().getIdEstado()));
			
				if(selectedOficina.getDireccion().getMunicipio() != null) {
					municipios = municipioRest.getByEstado(selectedOficina.getDireccion().getEstado());
					selectedOficina.getDireccion().setMunicipio(iteratorList.getMunicipioById(municipios, selectedOficina.getDireccion().getMunicipio().getIdMunicipio()));
				}
			}
			
			
			
		}
		
		if(selectedOficina.getGeolocalizacion() != null && (selectedOficina.getGeolocalizacion().getDescripcion() == null || selectedOficina.getGeolocalizacion().getDescripcion().isEmpty()) ) {
			String descripcion = "";
			if(selectedOficina.getDireccion() != null) {
				if(selectedOficina.getDireccion().getCalle() != null && !selectedOficina.getDireccion().getCalle().isEmpty()) {
					descripcion = selectedOficina.getDireccion().getCalle() + ", ";
				}
				if(selectedOficina.getDireccion().getColonia() != null && !selectedOficina.getDireccion().getColonia().isEmpty()) {
					descripcion += selectedOficina.getDireccion().getColonia() + ", ";
				}
				
				if(selectedOficina.getDireccion().getMunicipio() != null && !selectedOficina.getDireccion().getMunicipio().getNombre().isEmpty()) {
					descripcion += selectedOficina.getDireccion().getMunicipio().getNombre() + ", ";
				}
				
				if(selectedOficina.getDireccion().getEstado() != null && !selectedOficina.getDireccion().getEstado().getNombre().isEmpty()) {
					descripcion += selectedOficina.getDireccion().getEstado().getNombre() + ", ";
				}
				
				if(selectedOficina.getDireccion().getCp() != null && !selectedOficina.getDireccion().getCp().isEmpty()) {
					descripcion += selectedOficina.getDireccion().getCp() + ", ";
				}
				if(!descripcion.isEmpty())
					descripcion = descripcion.substring(0, (descripcion.length() - 2) );
				selectedOficina.getGeolocalizacion().setDescripcion(descripcion);
			}
				 
			
		}
		Clients.evalJavaScript("selectedCityJs('" + new Gson().toJson(selectedOficina.getGeolocalizacion())+ "')");
	}
	
	@Command
	@NotifyChange({"selectedOficina", "municipios"})
	public void clickedEstado() {
		municipios = municipioRest.getByEstado(selectedOficina.getDireccion().getEstado());
		selectedOficina.getDireccion().setMunicipio(null);
	}
	
	
	
	private void initProperties() {
		selectedOficina = new Oficina();
		Direccion dir = new Direccion();
		
		dir.setTelefono(new Telefono());
		selectedOficina.setDireccion(dir);
		
		selectedPais = iteratorList.getPaisByClave(paises, ConstAtributos.MX);
		
		selectedOficina.setGeolocalizacion(new Geolocalizacion());
		if(geoLat != null)
			geoLat.setValue(0D);
		if(geoLong != null)
			geoLong.setValue(0D);
		if(mapTransferTxt != null)
			mapTransferTxt.setText("");
		
		selectedOficina.setOrganizacion(organizacion);
		municipios = null;
	}
	
	private MessageControl validarFormulario(){
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_OFICINA_TITLE);
		if(selectedOficina == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_OFICINA_INICIALIZADO);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
		if(selectedOficina.getNombre() == null || selectedOficina.getNombre().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_OFICINA_NOMBRE);
			ctrlMessage.setComponentZk(ofNombreTxt);
			ofNombreTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(selectedOficina.getDireccion().getNumeroExterior() == null || selectedOficina.getDireccion().getNumeroExterior().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_NUM_EXT);
			ctrlMessage.setComponentZk(ofDirNoExtTxt);
			ofDirNoExtTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(selectedOficina.getDireccion().getEstado() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_EDO);
			ctrlMessage.setComponentZk(ofDirEstadoComboBox);
			ofDirEstadoComboBox.setFocus(true);
			return ctrlMessage;
		}
		
		if(selectedOficina.getDireccion().getMunicipio() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_MUNICIPIO);
			ctrlMessage.setComponentZk(ofDirMunComboBox);
			ofDirMunComboBox.setFocus(true);
			return ctrlMessage;
		}
		
		if(selectedOficina.getDireccion().getTelefono().getOficina() == null || selectedOficina.getDireccion().getTelefono().getOficina().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_TELEFONO);
			ctrlMessage.setComponentZk(ofDirtelTxt);
			ofDirtelTxt.setFocus(true);
			return ctrlMessage;
		}
		
		if(selectedOficina.getGeolocalizacion() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_GEOLOCALIZACION);
			ctrlMessage.setComponentZk(null);
			return ctrlMessage;
		}
		
//		selectedOficina.getGeolocalizacion().setLatitud(geoLat.getValue() != null && !geoLat.getValue().isEmpty() ? geoLat.getValue() : null);
//		selectedOficina.getGeolocalizacion().setLongitud(geoLong.getValue() != null && !geoLong.getValue().isEmpty() ? geoLong.getValue() : null);
		selectedOficina.getGeolocalizacion().setLatitud(geoLat.getValue() != null ? geoLat.getValue().toString() : null);
		selectedOficina.getGeolocalizacion().setLongitud(geoLong.getValue() != null ? geoLong.getValue().toString() : null);
		
		
		if((selectedOficina.getGeolocalizacion().getLatitud() == null || selectedOficina.getGeolocalizacion().getLatitud().isEmpty()) || (selectedOficina.getGeolocalizacion().getLongitud() == null || selectedOficina.getGeolocalizacion().getLongitud().isEmpty())) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_MSG_GENERICO_COORDENADAS);
			ctrlMessage.setComponentZk(geoLat);
			geoLat.setFocus(true);
			return ctrlMessage;
		}
		return null;
		
	}
}
