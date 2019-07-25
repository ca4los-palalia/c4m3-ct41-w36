package com.came.control.web.layer;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.beans.RefactoringJs;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Municipio;
import com.came.control.model.domain.Telefono;
import com.came.control.web.bean.MessageControl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LayerWebOperaciones extends LayerWebData {

	private static final long serialVersionUID = -1962806396639754556L;
	

//	public void showInformation(MessageControl ctrlMessage) {
//		Map<String, Object> inputParams = new HashMap<String, Object>();
//		inputParams.put("itemFinder", "showInformationPost");
//		inputParams.put("MessageControl", ctrlMessage);
//		
//		Window productoModalView = ctrlUtils.createModelDialogWithParams(
//				"/modal/information.zul", inputParams);
//		productoModalView.doModal();
//	}
//	
//	public void showError(MessageControl ctrlMessage) {
//		Map<String, Object> inputParams = new HashMap<String, Object>();
//		inputParams.put("itemFinder", "showInformationPost");
//		inputParams.put("MessageControl", ctrlMessage);
//		
//		Window productoModalView = ctrlUtils.createModelDialogWithParams(
//				"/modal/error.zul", inputParams);
//		productoModalView.doModal();
//	}
//	
//	public void showWarning(MessageControl ctrlMessage) {
//		Map<String, Object> inputParams = new HashMap<String, Object>();
//		inputParams.put("itemFinder", "showInformationPost");
//		inputParams.put("MessageControl", ctrlMessage);
//		
//		Window productoModalView = ctrlUtils.createModelDialogWithParams(
//				"/modal/warning.zul", inputParams);
//		productoModalView.doModal();
//	}
	
//	@GlobalCommand
//	//@NotifyChange({ "lugares", "lugar"})
//	public void showInformationPost(
//			@BindingParam("MessageControl") MessageControl messageControl) {
//		if(messageControl.getTextBoxZkZk() != null) {
//			Textbox txt = null;
//			Intbox intBox = null;
//			try {
//				txt = (Textbox) messageControl.getTextBoxZkZk();
//				txt.setFocus(true);
//				txt.select();
//			} catch (Exception e) {
//				intBox = (Intbox) messageControl.getTextBoxZkZk();
//				intBox.setFocus(true);
//				intBox.select();
//			}
//			
//			Clients.showNotification("Teclear valor", messageControl.getTextBoxZkZk());
//		}
//	}
	
	
	public MessageControl crearMessageContent(String message, String title) {
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setMensaje(message);
		ctrlMessage.setTitle(title);
		return ctrlMessage;
	}
	
	public Geolocalizacion splitterGeoDireccion(Geolocalizacion geo, String geoDireccion) {
		String [] sppliter = geoDireccion.split(",");
		if(sppliter.length == 4) {
			geo.setDirCalle(sppliter[0] == null ? "" : (sppliter[0].replace("\"", "").trim()) );
			geo.setDirColonia(sppliter[1] == null ? "" : (sppliter[1].replace("\"", "").trim()) );
			geo.setDirEstado(sppliter[2] == null ? "" : (sppliter[2].replace("\"", "").trim()) );
			geo.setDirCP(sppliter[3] == null ? "" : (sppliter[3].replace("\"", "").trim()) );
		} else {
			geo.setDirCalle(sppliter[0] == null ? "" : (sppliter[0].replace("\"", "").trim()) );
			geo.setDirColonia(sppliter[1] == null ? "" : (sppliter[1].replace("\"", "").trim()) );
			geo.setDirMunicipio(sppliter[2] == null ? "" : (sppliter[2].replace("\"", "").trim()) );
			geo.setDirEstado(sppliter[3] == null ? "" : (sppliter[3].replace("\"", "").trim()) );
			geo.setDirCP(sppliter[4] == null ? "" : (sppliter[4].replace("\"", "").trim()) );
		}
		return geo;
	}
	
	public Geolocalizacion splitterGeoDireccionMark(Geolocalizacion geo, String geoDireccion) {
		geoDireccion = geoDireccion.trim().replace("\"", "");
		geo.setDescripcion(geoDireccion);
		
		String [] sppliter = geoDireccion.split(",");
		sppliter = checkPaisEmpty(sppliter);
		
		
		List<String> reorderList = new ArrayList<String>();
		if(sppliter.length > 0) {
			//geo.setDescripcion(null);
			for (int i = sppliter.length; i > 0 ; i--)
				reorderList.add(sppliter[i-1].trim());
			
			if(reorderList.size() > 5) {
				for (String itemDir : reorderList) {
					if(geo.getDirPais() == null || geo.getDirPais().isEmpty())
						geo.setDirPais(itemDir);
					else if(geo.getDirCP() == null || geo.getDirCP().isEmpty())
						geo.setDirCP(itemDir);
					else if(geo.getDirEstado() == null || geo.getDirEstado().isEmpty())
						geo.setDirEstado(itemDir);
					else if(geo.getDirMunicipio() == null || geo.getDirMunicipio().isEmpty())
						geo.setDirMunicipio(itemDir);
					else if(geo.getDirColonia() == null || geo.getDirColonia().isEmpty())
						geo.setDirColonia(itemDir);
					else if(geo.getDirCalle() == null || geo.getDirCalle().isEmpty())
						geo.setDirCalle(itemDir);
				}
			} else if(reorderList.size() == 5) {
				for (String itemDir : reorderList) {
					if(geo.getDirPais() == null || geo.getDirPais().isEmpty())
						geo.setDirPais(itemDir);
					else if(geo.getDirCP() == null || geo.getDirCP().isEmpty())
						geo.setDirCP(itemDir);
					else if(geo.getDirEstado() == null || geo.getDirEstado().isEmpty())
						geo.setDirEstado(itemDir);
					else if(geo.getDirMunicipio() == null || geo.getDirMunicipio().isEmpty())
						geo.setDirMunicipio(itemDir);
					else if(geo.getDirColonia() == null || geo.getDirColonia().isEmpty())
						geo.setDirColonia(itemDir);
				}
			} else if(reorderList.size() == 4) {
				int j = 0;
				boolean existCp = false;
				for (String itemDir : reorderList) {
					switch (j) {
					case 0:
						geo.setDirPais(itemDir);
						break;
					case 1:
						try {
							Integer.parseInt(itemDir);
							geo.setDirCP(itemDir);
							existCp = true;
						} catch (Exception e) {
							geo.setDirEstado(itemDir);
						}
						break;
					case 2:
						if(existCp)
							geo.setDirEstado(itemDir);
						else
							geo.setDirMunicipio(itemDir);
						break;
					case 3:
						if(existCp)
							geo.setDirMunicipio(itemDir);
						else
							geo.setDirColonia(itemDir);
						break;
					}
					j ++;
				}
			} else if(reorderList.size() == 3) {
				int j = 0;
				boolean existCp = false;
				for (String itemDir : reorderList) {
					switch (j) {
					case 0:
						geo.setDirPais(itemDir);
						break;
					case 1:
						try {
							Integer.parseInt(itemDir);
							geo.setDirCP(itemDir);
							existCp = true;
						} catch (Exception e) {
							geo.setDirEstado(itemDir);
						}
						break;
					case 2:
						if(existCp)
							geo.setDirMunicipio(itemDir);
						else
							geo.setDirColonia(itemDir);
						break;
					}
					j ++;
				}
			} else if(reorderList.size() == 2) {
				int j = 0;
				for (String itemDir : reorderList) {
					switch (j) {
					case 0:
						geo.setDirPais(itemDir);
						break;
					case 1:
							geo.setDirEstado(itemDir);
							geo.setDirMunicipio(itemDir);
						break;
					}
					j ++;
				}
			} else if(reorderList.size() == 1) {
				int j = 0;
				for (String itemDir : reorderList) {
					switch (j) {
					case 0:
						geo.setDirPais(itemDir);
						geo.setDirEstado(itemDir);
						geo.setDirMunicipio(itemDir);
						break;
					}
					j ++;
				}
			}
			
			
			
		}
		return geo;
	}
	
	private String [] checkPaisEmpty(String [] sppliter) {
		List<String> listTemp = new ArrayList<String>(Arrays.asList(sppliter));
		try {
			int cp = Integer.parseInt(listTemp.get(listTemp.size() - 1).trim());
			Estado edo = (Estado) estadoRest.getByNombre(listTemp.get(listTemp.size() - 2).trim()).getSingle();
			if(edo != null)
			listTemp.add(edo.getPais() == null ? "" : edo.getPais().getNombre());
			
			return listTemp.toArray(new String[0]);
		} catch (Exception e) {
			return sppliter;
		}
	}
	
	
	@Command
	@NotifyChange({"*"})
	public void pushSelectedCityCurrentLocation() {
		TypeToken<List<Geolocalizacion>> token = new TypeToken<List<Geolocalizacion>>() {};
		List<Geolocalizacion> listObj = new Gson().fromJson(mapTransferTxt.getText(), token.getType());
		Geolocalizacion geolocalizacionSelected = listObj.get(0);
		geolocalizacionSelected = splitterGeoDireccionMark(geolocalizacionSelected, geolocalizacionSelected.getDescripcion());
		geolocalizacionSelected = createAtributosGeolocalizacionFromJavaScript(geolocalizacionSelected);
		Clients.evalJavaScript("selectedCityJs('" + new Gson().toJson(geolocalizacionSelected)+ "')");
	}
	
	
	@Command
	public void pushSelectedCity() {
		TypeToken<List<Geolocalizacion>> token = new TypeToken<List<Geolocalizacion>>() {};
		List<Geolocalizacion> list = new Gson().fromJson(mapTransferTxt.getText(), token.getType());;
		mapTransferTxt.setText("");
		
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "postSelectedCity");
		inputParams.put("list", list);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/showAddressFinder.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	@NotifyChange({"*"})
	//@NotifyChange({"selectedOficina", "estados", "municipios"})
	public void postSelectedCity(@BindingParam("geolocalizacionSelected") Geolocalizacion geolocalizacionSelected) {
		geolocalizacionSelected = splitterGeoDireccionMark(geolocalizacionSelected, geolocalizacionSelected.getDescripcion());
		geolocalizacionSelected = createAtributosGeolocalizacionFromJavaScript(geolocalizacionSelected);
		
//		Direccion dir = new Direccion();
//		dir.setTelefono(new Telefono());
//		
//		dir.setCalle(geolocalizacionSelected.getDirCalle());
//		dir.setCp(geolocalizacionSelected.getDirCP());
//		
//		if(geolocalizacionSelected.getDirMunicipio() != null && !geolocalizacionSelected.getDirMunicipio().isEmpty() && geolocalizacionSelected.getDirMunicipio().toLowerCase().contains("municipio de")) {
//			String [] splitterMun = geolocalizacionSelected.getDirMunicipio().split(" ");
//			String concater = "";
//			for (int i = 0; i < splitterMun.length; i++) {
//				if(i > 1)
//					concater += splitterMun[i] + " ";
//			}
//			geolocalizacionSelected.setDirMunicipio(concater.trim());
//		}
//		
//		if(geolocalizacionSelected.getDirEstado() != null && !geolocalizacionSelected.getDirEstado().isEmpty()) {
//			
//			Estado edo = null;
//			if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_BCAL))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_BCAL_DB).getSingle();
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_BCAL_S))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_BCAL_S_DB).getSingle();
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_CMX))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_CMX_DB).getSingle();
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_MEX))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_MEX_DB).getSingle();
//			
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_N_LEON))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_N_LEON_DB).getSingle();
//			
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_Q_ROO))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_Q_ROO_DB).getSingle();
//			
//			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_S_LUIS_P))
//				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_S_LUIS_P_DB).getSingle();
//			
//			else {
//				String [] splitter = geolocalizacionSelected.getDirEstado().split(" ");
//				edo = (Estado) estadoRest.getByNombre(splitter[0]).getSingle();
//			}
//			
//			if(edo != null) {
//				if(estados == null)
//					estados = (List<Estado>)estadoRest.getAll().getSingle();
//				
//				edo = iteratorList.getEstadoById(estados, edo.getIdEstado());
//				dir.setEstado(edo);
//				
//				municipios = (List<Municipio>)municipioRest.getByEstado(edo).getSingle();
//				Municipio mun = (Municipio) municipioRest.getByNombre(geolocalizacionSelected.getDirMunicipio()).getSingle();
//				if(mun != null) {
//					mun = iteratorList.getMunicipioById(municipios, mun.getIdMunicipio());
//					dir.setMunicipio(mun);
//				}
//			}
//		}
//		dir.setColonia(geolocalizacionSelected.getDirColonia());
//		geolocalizacionSelected.setDir(dir);
		Clients.evalJavaScript("selectedCityJs('" + new Gson().toJson(geolocalizacionSelected)+ "')");
	}
	
	public Geolocalizacion createAtributosGeolocalizacionFromJavaScript(Geolocalizacion geolocalizacionSelected) {
		Direccion dir = new Direccion();
		dir.setTelefono(new Telefono());
		
		dir.setCalle(geolocalizacionSelected.getDirCalle());
		dir.setCp(geolocalizacionSelected.getDirCP());
		
		if(geolocalizacionSelected.getDirMunicipio() != null && !geolocalizacionSelected.getDirMunicipio().isEmpty() && geolocalizacionSelected.getDirMunicipio().toLowerCase().contains("municipio de")) {
			String [] splitterMun = geolocalizacionSelected.getDirMunicipio().split(" ");
			String concater = "";
			for (int i = 0; i < splitterMun.length; i++) {
				if(i > 1)
					concater += splitterMun[i] + " ";
			}
			geolocalizacionSelected.setDirMunicipio(concater.trim());
		}
		
		if(geolocalizacionSelected.getDirEstado() != null && !geolocalizacionSelected.getDirEstado().isEmpty()) {
			
			Estado edo = null;
			if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_BCAL))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_BCAL_DB).getSingle();
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_BCAL_S))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_BCAL_S_DB).getSingle();
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_CMX))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_CMX_DB).getSingle();
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_MEX))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_MEX_DB).getSingle();
			
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_N_LEON))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_N_LEON_DB).getSingle();
			
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_Q_ROO))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_Q_ROO_DB).getSingle();
			
			else if(geolocalizacionSelected.getDirEstado().contentEquals(ConstAtributos.EDO_S_LUIS_P))
				edo = (Estado) estadoRest.getByNombre(ConstAtributos.EDO_S_LUIS_P_DB).getSingle();
			
			else {
				String [] splitter = geolocalizacionSelected.getDirEstado().split(" ");
				edo = (Estado) estadoRest.getByNombre(splitter[0]).getSingle();
			}
			
			if(edo != null) {
				if(estados == null)
					estados = (List<Estado>)estadoRest.getAll().getSingle();
				
				edo = iteratorList.getEstadoById(estados, edo.getIdEstado());
				dir.setEstado(edo);
				
				municipios = municipioRest.getByEstado(edo);
				Municipio mun = (Municipio) municipioRest.getByNombre(geolocalizacionSelected.getDirMunicipio());
				if(mun != null) {
					mun = iteratorList.getMunicipioById(municipios, mun.getIdMunicipio());
					dir.setMunicipio(mun);
				}
			}
		}
		dir.setColonia(geolocalizacionSelected.getDirColonia());
		geolocalizacionSelected.setDir(dir);
		return geolocalizacionSelected;
	}
	
	public RefactoringJs refactorarJsInfoDetected(Direccion dirOrigen, Direccion dirModificado, Geolocalizacion geoOrigen, Geolocalizacion geoModificado) {
		if(dirOrigen == null || dirOrigen.getIdDireccion() == null) {
			if(dirModificado.getMunicipio() != null)
				dirModificado.setMunicipio(iteratorList.getMunicipioById(municipios, dirModificado.getMunicipio().getIdMunicipio()));
			
			if(dirModificado.getCalle() == null || dirModificado.getCalle().isEmpty())
				dirModificado.setCalle(dirOrigen != null ? dirOrigen.getCalle() : null);
			if(dirModificado.getNumeroInterior() == null || dirModificado.getNumeroInterior().isEmpty())
				dirModificado.setNumeroInterior(dirOrigen != null ? dirOrigen.getNumeroInterior() : null);
			if(dirModificado.getNumeroExterior() == null || dirModificado.getNumeroExterior().isEmpty())
				dirModificado.setNumeroExterior(dirOrigen != null ? dirOrigen.getNumeroExterior() : null);
			if(dirModificado.getColonia() == null || dirModificado.getColonia().isEmpty())
				dirModificado.setColonia(dirOrigen != null ? dirOrigen.getColonia() : null);
			if(dirModificado.getCp() == null || dirModificado.getCp().isEmpty())
				dirModificado.setCp(dirOrigen != null ? dirOrigen.getCp() : null);
			if(dirModificado.getTelefono().getOficina() == null || dirModificado.getTelefono().getOficina().isEmpty())
				dirModificado.getTelefono().setOficina(dirOrigen.getTelefono().getOficina());
			dirOrigen = dirModificado;
		} else {
			if(dirModificado != null) {
				dirOrigen.setCalle(dirModificado.getCalle());
				dirOrigen.setColonia(dirModificado.getColonia());
				dirOrigen.setCp(dirModificado.getCp());
				dirOrigen.setEstado(iteratorList.getEstadoById(estados, dirModificado.getEstado().getIdEstado()));
				
				municipios = municipioRest.getByEstado(dirOrigen.getEstado());
				
				dirOrigen.setMunicipio(iteratorList.getMunicipioById(municipios, dirModificado.getMunicipio().getIdMunicipio()));
				if(dirOrigen.getTelefono() == null || dirOrigen.getTelefono().getIdTelefono() == null)
					dirOrigen.setTelefono(dirModificado.getTelefono());
					
			} else {
				municipios = municipioRest.getByEstado(dirOrigen.getEstado());
				if(dirOrigen.getMunicipio() != null) {
					dirOrigen.setMunicipio(iteratorList.getMunicipioById(municipios, dirOrigen.getMunicipio().getIdMunicipio()));
				}
			}
			
			geoOrigen.setDescripcion(geoModificado.getDescripcion());
			geoOrigen.setDirCalle(geoModificado.getDirCalle());
			geoOrigen.setDirColonia(geoModificado.getDirColonia());
			geoOrigen.setDirCP(geoModificado.getDirCP());
			geoOrigen.setDirEstado(geoModificado.getDirEstado());
			geoOrigen.setDirMunicipio(geoModificado.getDirMunicipio());
			geoOrigen.setDirPais(geoModificado.getDirPais());
			geoOrigen.setLatitud(geoModificado.getLatitud());
			geoOrigen.setLongitud(geoModificado.getLongitud());
			
		}
		RefactoringJs ref = new RefactoringJs();
		ref.setDireccion(dirOrigen);
		ref.setGeolocalizacion(geoOrigen);
		return ref;
	}
	
	/**
	 * @param formato HH:mm
	 * @return Calendar con fecha actual y tiempo modificado de acuerdo al parametro
	 * **/
	public Calendar builderCalendarFromTime(String hora, Calendar fechaActual) {
		String [] splitter = hora.replace(":", "-").split("-");
		return ctrlUtils.getFechaControladaCalendarCambiarHr(fechaActual, new Integer(splitter[0]), new Integer(splitter[1]));
	}
	

	public String getDiaDeLaSemana(Calendar calendar) {
		if(calendar != null) {
			return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale ("es","ES")).toUpperCase();
		}
		return null;
	}
	
	public static String limpiarAcentos(String cadena) {
	    String limpio =null;
	    if (cadena !=null) {
	        String valor = cadena;
	        //valor = valor.toUpperCase();
	        // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
	        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
	        // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
	        limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
	        // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
	        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
	    }
	    return limpio;
	}
	
}
