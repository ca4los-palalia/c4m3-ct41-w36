package com.came.control.web.vm.colaboradores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Pais;
import com.came.control.model.domain.Persona;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.Telefono;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;

@VariableResolver({ DelegatingVariableResolver.class })
public class ColaboradoresVM extends LayerWebOperaciones {
	
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabClave")
	private Textbox txtColabClave;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabNomUsuario")
	private Textbox txtColabNomUsuario;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabPass")
	private Textbox txtColabPass;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabpass2")
	private Textbox txtColabpass2;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabPersNombre")
	private Textbox txtColabPersNombre;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabPersApPat")
	private Textbox txtColabPersApPat;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabPersApMat")
	private Textbox txtColabPersApMat;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #dateboxColabPersNacimiento")
	private Datebox dateboxColabPersNacimiento;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgSexo")
	private Combobox comboboxColabDgSexo;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgGpoSangui")
	private Combobox comboboxColabDgGpoSangui;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgEdoCivil")
	private Combobox comboboxColabDgEdoCivil;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgRfc")
	private Textbox txtColabDgRfc;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgNss")
	private Textbox txtColabDgNss;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgCurp")
	private Textbox txtColabDgCurp;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabTipoEmpleado")
	private Combobox comboboxColabTipoEmpleado;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirTelCasa")
	private Textbox txtColabDgDirTelCasa;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirTelMovil")
	private Textbox txtColabDgDirTelMovil;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabOFicina")
	private Combobox comboboxColabOFicina;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #btnVerHorario")
	private Button btnVerHorario;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabEstatus")
	private Combobox comboboxColabEstatus;
	
	
	
	
	
	
	
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgCorreo")
	private Textbox txtColabDgCorreo;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirCalle")
	private Textbox txtColabDgDirCalle;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirNumInt")
	private Textbox txtColabDgDirNumInt;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirNumExt")
	private Textbox txtColabDgDirNumExt;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirCol")
	private Textbox txtColabDgDirCol;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #txtColabDgDirCp")
	private Textbox txtColabDgDirCp;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgDirPais")
	private Combobox comboboxColabDgDirPais;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgDirEstado")
	private Combobox comboboxColabDgDirEstado;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabDgDirMun")
	private Combobox comboboxColabDgDirMun;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabLugarNacimiento")
	private Combobox comboboxColabLugarNacimiento;
	
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxColab, #comboboxColabNacionalidad")
	private Combobox comboboxColabNacionalidad;
	
	
	
	
	
	
	
	
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxEmergencia, #txtNombrePersEmerg")
	private Textbox txtNombrePersEmerg;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxEmergencia, #txtApPatPersEmerg")
	private Textbox txtApPatPersEmerg;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxEmergencia, #txtApMatPersEmerg")
	private Textbox txtApMatPersEmerg;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxEmergencia, #txtMovilPersEmerg")
	private Textbox txtMovilPersEmerg;
	@Wire("#colaboradoresvm, #blmain, #centerPanelData, #grpBxResponsable, #comboResponsable")
	private Combobox comboResponsable;
	@Wire("#colaboradoresvm, #centerPanelData, #grpBxPrivileg, #gridPrivilege")
	private Grid gridPrivilege;
	@Wire("#colaboradoresvm, #btnNuevo")
	private Button btnNuevo;
	
	
	

//	private boolean checkAllInJornada;
//	private boolean checkAllOutJornada;
//	private boolean checkAllInComida;
//	private boolean checkAllOutComida;

	private static final long serialVersionUID = -2191028651783882559L;

	@SuppressWarnings("unchecked")
	@Init
	public void init() {
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		moduloSession = ((Modulo) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_MODULO_USUARIO));

		estadosCiviles = (List<EstadoCivil>) estadoCivilRest.getAll().getSingle();
		gruposSanguineos = (List<GrupoSanguineo>) grupoSanguineoRest.getAll().getSingle();

		oficinas = (List<Oficina>) oficinaRest.getByOrganizacion(organizacion).getSingle();
		paises = (List<Pais>) paisRest.getAll().getSingle();
		selectedPais = iteratorList.getPaisByClave(paises, ConstAtributos.MX);
		estados = (List<Estado>) estadoRest.getAll().getSingle();
		sexos = (List<Sexo>) sexoRest.getAll().getSingle();
		responsables = usuarioRest.getByRol(rolRest.getByNombre(ConstAtributos.ROL_GERENTE), organizacion);
		nacionalidades = nacionalidadRest.getAll();
		escolaridades = escolaridadRest.getAllByOrganizacion(organizacion);
		metodosPago = metodoPagoRest.getAllByOrganizacion(organizacion);
		bancos = bancoRest.getAllByOrganizacion(organizacion);
		
		
		List<Rol> rolesLocal = rolRest.getByOrganizacion(organizacion);
		roles = new ArrayList<Rol>();
		for (Rol rol : rolesLocal) {
			if (!rol.getNombre().contentEquals(ConstAtributos.ROL_ADMIN)
					&& !rol.getNombre().contentEquals(ConstAtributos.ROL_SYSADMIN))
				roles.add(rol);
		}
		estatusList = (List<Estatus>) estatusRest.getAll().getSingle();
		

		initProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		if (txtColabNomUsuario != null)
			txtColabNomUsuario.setFocus(true);
	}

	private void initProperties() {
		selectedColaborador = new Usuario();
		selectedColaborador.setBtnHorarioLabel("Asignar horario");
		selectedColaborador.setBtnHorarioSclass("btnClear btnAddBorder btnFormatLabel btnViewer2");

		// ***************************
//		List<Horario> listH = iteratorList.crearHorarioTemporal(null,
//				iteratorList.getZonaHorarioByZh(zonasHorarios, ConstAtributos.ZH_MEXICO_CITY));
//		for (Horario item : listH) {
//			item.setZhList(zonasHorarios);
//		}
//		selectedColaborador.setHorario(listH);
		// ***************************

		DatosGenerales dg = new DatosGenerales();
		Direccion dir = new Direccion();

		Estado edo = new Estado();
		edo.setPais(new Pais());
		dir.setEstado(edo);
		dir.setTelefono(new Telefono());
		dg.setDireccion(dir);
		dg.setSexo(new Sexo());
		Emergencia emr = new Emergencia();
		emr.setPersona(new Persona());
		emr.setTelefono(new Telefono());

		dg.setEmergencia(emr);

		dg.setEstadoCivil(new EstadoCivil());
		dg.setGrupoSanguineo(new GrupoSanguineo());
		dg.setOrganizacion(organizacion);
		dg.setPersona(new Persona());
		dg.setResponsable(new Usuario());
		selectedColaborador.setDatosGenerales(dg);

		selectedColaborador.setEstatus(new Estatus());

		selectedColaborador
				.setImagen(ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "userTemplate.png");
		selectedColaborador.setOficina(new Oficina());
		selectedColaborador.setOrganizacion(organizacion);
		selectedColaborador.setRol(new Rol());
		selectedColaborador.setFechaContratacion(new Date());
		selectedColaborador.setFechaAlta(new Date());

		List<Modulo> listItem = moduloRest.getAll();
		List<ModuloUsuario> itemMU = new ArrayList<ModuloUsuario>();
		for (Modulo item : listItem) {
			ModuloUsuario nmu = new ModuloUsuario();
			nmu.setActivar(false);
			nmu.setModulo(item);
			nmu.setUsuario(selectedColaborador);
			itemMU.add(nmu);
		}
		selectedColaborador.setModulosUsuario(itemMU);

		selectedPais = null;
		selectedEstado = null;
		selectedMunicipio = null;
		selectedOficina = null;
		selectedResponsable = null;

		if (txtColabClave != null)
			txtColabClave.setFocus(true);
	}

	@Command
	@NotifyChange("selectedColaborador")
	public void uploadLogo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@BindingParam("compOrgCp") Component comp) {
		if (selectedColaborador == null) {
			Clients.showNotification("Inicialice el colaborador previamente", Clients.NOTIFICATION_TYPE_WARNING, btnNuevo, null, 0);
			btnNuevo.setFocus(true);
			return;
		}

		if (selectedColaborador.getDatosGenerales().getRfc() == null) {
			Clients.showNotification("Debe asignar previamente el RFC del colaborador", Clients.NOTIFICATION_TYPE_WARNING, txtColabDgRfc, null, 0);
			txtColabDgRfc.setFocus(true);
			return;
		}

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			// int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
//				String contentType = media.getContentType().contains(ConstAtributos.PNG)
//						? ConstAtributos.CONTENT_TYPE.get(ConstAtributos.PNG)
//								: ConstAtributos.CONTENT_TYPE.get(ConstAtributos.JPEG);

				String pathOutput = ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_USUARIOS
						+ selectedColaborador.getDatosGenerales().getRfc() + "." + media.getFormat();
				// Filedownload.save(media.getByteData(), contentType, pathOutput);
				try {
					FileUtils.writeByteArrayToFile(new File(pathOutput), media.getByteData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selectedColaborador.setImagen(pathOutput);
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "estados", "selectedEstado", "municipios", "selectedMunicipio" })
	@Command
	public void clickedPais() {
		municipios = null;
		selectedMunicipio = null;
		estados = (List<Estado>) estadoRest.getByPais(selectedPais).getSingle();
		selectedEstado = null;
	}

	@SuppressWarnings("unchecked")
	@NotifyChange({ "municipios", "selectedMunicipio" })
	@Command
	public void clickedEstado() {
		municipios = municipioRest.getByEstado(selectedEstado);
		selectedMunicipio = null;
	}

	@NotifyChange({ "selectedColaborador", "selectedPais", "selectedEstado", "selectedMunicipio", "selectedOficina",
			"selectedResponsable" })
	@Command
	public void clickedNuevo() {
		initProperties();
	}

	@NotifyChange({ "selectedColaborador", "selectedPais", "selectedEstado", "selectedMunicipio", "selectedOficina",
			"selectedResponsable" })
	@Command
	public void clickedSave() {
		MessageControl validador = validarFormulario();
		if (validador == null) {
			selectedColaborador.getDatosGenerales().setRfc(selectedColaborador.getDatosGenerales().getRfc().toUpperCase());
			selectedColaborador.getDatosGenerales().setNss(selectedColaborador.getDatosGenerales().getNss().toUpperCase());
			selectedColaborador.getDatosGenerales().setCurp(selectedColaborador.getDatosGenerales().getCurp().toUpperCase());
			
			selectedColaborador.getDatosGenerales().setResponsable(selectedResponsable);
			selectedColaborador.setOficina(selectedOficina);
			selectedColaborador.getDatosGenerales().getDireccion().setEstado(selectedEstado);
			selectedColaborador.getDatosGenerales().getDireccion().setMunicipio(selectedMunicipio);

			if (selectedColaborador.getNombreUsuario() == null || selectedColaborador.getNombreUsuario().isEmpty())
				selectedColaborador.setNombreUsuario(
						ctrlUtils.Encriptar(selectedColaborador.getDatosGenerales().getRfc().toUpperCase()));
			if (selectedColaborador.getContrasenia() == null || selectedColaborador.getContrasenia().isEmpty())
				selectedColaborador.setContrasenia(ctrlUtils.Encriptar(passwordGenerator.getPassword(5)));

			selectedColaborador.getDatosGenerales().getDireccion().setTelefono((Telefono) telefonoRest
					.save(selectedColaborador.getDatosGenerales().getDireccion().getTelefono()).getSingle());
			selectedColaborador.getDatosGenerales().setDireccion(
					(Direccion) direccionRest.save(selectedColaborador.getDatosGenerales().getDireccion()).getSingle());
			selectedColaborador.getDatosGenerales().getEmergencia().setPersona((Persona) personaRest
					.save(selectedColaborador.getDatosGenerales().getEmergencia().getPersona()).getSingle());
			selectedColaborador.getDatosGenerales().getEmergencia().setTelefono((Telefono) telefonoRest
					.save(selectedColaborador.getDatosGenerales().getEmergencia().getTelefono()).getSingle());
			selectedColaborador.getDatosGenerales().setEmergencia((Emergencia) emergenciaRest
					.save(selectedColaborador.getDatosGenerales().getEmergencia()).getSingle());
			selectedColaborador.getDatosGenerales().setPersona(
					(Persona) personaRest.save(selectedColaborador.getDatosGenerales().getPersona()).getSingle());
			selectedColaborador.setDatosGenerales(
					(DatosGenerales) datosGeneralesRest.save(selectedColaborador.getDatosGenerales()).getSingle());

			List<Horario> horarioList = selectedColaborador.getHorarios();
			List<ModuloUsuario> listTemp = selectedColaborador.getModulosUsuario();
			selectedColaborador.setModulosUsuario(null);
			selectedColaborador.setHorarios(null);
			
			//usuarioService.save(selectedColaborador);
			selectedColaborador = usuarioRest.save(selectedColaborador);
			
			for (ModuloUsuario item : listTemp) {
				item.setUsuario(selectedColaborador);
				item = moduloUsuarioRest.save(item);
			}
			
			if(horarioList != null)
				for (Horario horario : horarioList)
					horario = horarioRest.save(horario);
			selectedColaborador.setModulosUsuario(listTemp);
			selectedColaborador.setHorarios(horarioList);

			String saveOrUpdate = "Usuario " + selectedColaborador.getDatosGenerales().getPersona().getNombreCompleto() + selectedColaborador.getIdUsuario() == null ? " creado."
					: " actualizado.";

//			MessageControl ctrlMessage = new MessageControl();
//			ctrlMessage.setMensaje(
//					"El colaborador " + selectedColaborador.getDatosGenerales().getPersona().getNombreCompleto() + " ha sido "
//							+ saveOrUpdate + "\nLas credenciales de acceso para este colaborador son:\n" + "\rUsuario: "
//							+ selectedColaborador.getDekodifikatorUsr() + "\n" + "\rContraseña: "
//							+ selectedColaborador.getDekodifikatorPass());
//			ctrlMessage.setTitle("Usuario " + saveOrUpdate);
			
			Clients.showNotification(saveOrUpdate, Clients.NOTIFICATION_TYPE_INFO, null, null, 0);

			initProperties();
		} else {
			Clients.showNotification(validador.getMensaje(), Clients.NOTIFICATION_TYPE_WARNING, validador.getComponentZk(), null, 0);
			if(validador.getComponentZk() != null)
				try {
					Textbox txt = (Textbox) validador.getComponentZk();
					txt.setFocus(true);
				} catch (Exception e) {
				}
			//showWarning(validador);
		}
	}

	@Command
	public void clickedBuscador() {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "showColaboradorSelected");
		inputParams.put("org", organizacion);

		Window productoModalView = ctrlUtils.createModelDialogWithParams("/modal/buscadorColaboradores.zul",
				inputParams);
		productoModalView.doModal();
	}

	@NotifyChange({ "selectedColaborador"})
	@Command
	public void clickedInJornada(@BindingParam("index") Integer index) {
//		Horario hSelected = selectedColaborador.getHorario().get(index);
//		
//		if(checkAllInJornada) {
//			for (Horario item : selectedColaborador.getHorario()) {
//				item.sethEntrada(hSelected.gethEntrada());
//			}
//		}
	}
	
	
	@NotifyChange({ "selectedColaborador"})
	@Command
	public void clickedOutJornada() {
		
	}
	
	@NotifyChange({ "selectedColaborador"})
	@Command
	public void clickedInComida() {
		
	}
	
	
	@NotifyChange({ "selectedColaborador"})
	@Command
	public void clickedOutComida() {
		
	}

	@GlobalCommand
	@NotifyChange({ "selectedColaborador", "selectedEstado", "selectedPais", "selectedMunicipio", "selectedOficina",
			"selectedResponsable", "municipios", "roles", "estatusList" })
	public void showColaboradorSelected(@BindingParam("usr") Usuario usr) {

		List<ModuloUsuario> listModulos = moduloUsuarioRest.getByUsuario(usr);
		List<Horario> listH = horarioRest.getByUsuario(usr);
		if(listH != null) {
			usr.setBtnHorarioLabel("Modificar horario");
			usr.setBtnHorarioSclass("btnClear btnAddBorder btnFormatLabel btnViewer1");
			for (Horario item : listH)
				item.setZonaHorario(item.getZonaHorario() != null ?
						iteratorList.getZonaHorarioById(zonasHorarios, item.getZonaHorario().getIdZonaHorario()) : null);
			usr.setHorarios(listH);
		}
		
		//**********************
		
		usr.getDatosGenerales().setBanco(usr.getDatosGenerales().getBanco() != null ? iteratorList.getBancoById(bancos, usr.getDatosGenerales().getBanco().getIdBanco()) : null);
		usr.getDatosGenerales().setEscolaridad(usr.getDatosGenerales().getEscolaridad() != null ? iteratorList.getEscolaridadById(escolaridades, usr.getDatosGenerales().getEscolaridad().getIdEscolaridad()) : null);
		usr.getDatosGenerales().setMetodoPago(usr.getDatosGenerales().getMetodoPago() != null ? iteratorList.getMetodoPagoById(metodosPago, usr.getDatosGenerales().getMetodoPago().getIdMetodoPago()) : null);
		usr.getDatosGenerales().setNacionalidad(usr.getDatosGenerales().getMetodoPago() != null ? iteratorList.getNacionalidadById(nacionalidades, usr.getDatosGenerales().getNacionalidad().getIdNacionalidad()) : null);
		

		//**********************
		municipios = usr.getDatosGenerales().getDireccion().getEstado() != null ? municipioRest.getByEstado(usr.getDatosGenerales().getDireccion().getEstado()) : null; 

		selectedEstado = iteratorList.getEstadoById(estados,
				(usr.getDatosGenerales().getDireccion().getEstado() != null
						? usr.getDatosGenerales().getDireccion().getEstado().getIdEstado()
						: 0L));
		selectedPais = iteratorList.getPaisById(paises,
				(usr.getDatosGenerales().getDireccion().getEstado().getPais() != null
						? usr.getDatosGenerales().getDireccion().getEstado().getPais().getIdPais()
						: 0L));

		if (usr.getDatosGenerales().getDireccion().getMunicipio() != null
				&& usr.getDatosGenerales().getDireccion().getMunicipio().getIdMunicipio() != null) {
			municipios = municipioRest.getByEstado(selectedEstado);
			selectedMunicipio = iteratorList.getMunicipioById(municipios,
					usr.getDatosGenerales().getDireccion().getMunicipio().getIdMunicipio());
		}
		if (usr.getOficina() != null && usr.getOficina().getIdOficina() != null)
			selectedOficina = iteratorList.getOficinaId(oficinas, usr.getOficina().getIdOficina());

		if (usr.getDatosGenerales().getResponsable() != null
				&& usr.getDatosGenerales().getResponsable().getIdUsuario() != null)
			selectedResponsable = iteratorList.getUsuarioId(responsables,
					usr.getDatosGenerales().getResponsable().getIdUsuario());

		if (usr.getDatosGenerales().getSexo() != null && usr.getDatosGenerales().getSexo().getIdSexo() != null)
			usr.getDatosGenerales()
					.setSexo(iteratorList.getSexoById(sexos, usr.getDatosGenerales().getSexo().getIdSexo()));

		if (usr.getDatosGenerales().getGrupoSanguineo() != null
				&& usr.getDatosGenerales().getGrupoSanguineo().getIdGrupoSanguineo() != null)
			usr.getDatosGenerales().setGrupoSanguineo(iteratorList.getGrupoSanguineoById(gruposSanguineos,
					usr.getDatosGenerales().getGrupoSanguineo().getIdGrupoSanguineo()));

		if (usr.getDatosGenerales().getEstadoCivil() != null
				&& usr.getDatosGenerales().getEstadoCivil().getIdEstadoCivil() != null)
			usr.getDatosGenerales().setEstadoCivil(iteratorList.getEstadoCivilById(estadosCiviles,
					usr.getDatosGenerales().getEstadoCivil().getIdEstadoCivil()));

		if (usr.getRol() != null && usr.getRol().getIdRol() != null)
			usr.setRol(iteratorList.getRolByNombre(roles, usr.getRol().getNombre()));

		if (usr.getEstatus() != null && usr.getEstatus().getIdEstatus() != null)
			usr.setEstatus(iteratorList.getEstatusId(estatusList, usr.getEstatus().getIdEstatus()));

		
		
		selectedColaborador = usr;
		selectedColaborador.setModulosUsuario(listModulos);
	}
	
	
	@Command
	public void verHorario() {
//		if(selectedOficina == null ) {
//			Clients.showNotification("Seleccione una oficina previamente", Clients.NOTIFICATION_TYPE_WARNING, comboboxColabOFicina, null, 0);
//			return ;
//		}
		
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("itemFinder", "configurarHorarioSelected");
		
		inputParams.put("selectedUsr", selectedColaborador);
		inputParams.put("readOnly", false);
		inputParams.put("buttonVisible", true);
		inputParams.put("org", organizacion);
		
		Window productoModalView = ctrlUtils.createModelDialogWithParams(
				"/modal/configurarHorario.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	@NotifyChange({"selectedColaborador"})
	public void configurarHorarioSelected(
			@BindingParam("lista") List<Horario> itemSelected) {
		selectedColaborador.setHorarios(itemSelected);
		selectedColaborador.setBtnHorarioLabel("Modificar horario");
		selectedColaborador.setBtnHorarioSclass("btnClear btnAddBorder btnFormatLabel btnViewer1");
		Clients.showNotification( ("Ha sido asignado un horario <b>al trabajador</b>"), Clients.NOTIFICATION_TYPE_INFO, null, null, 0);
	}

	private MessageControl validarFormulario() {
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_COLAB_TITLE);
		if (selectedColaborador.getClave() == null || selectedColaborador.getClave().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_CLAVE);
			ctrlMessage.setComponentZk(txtColabClave);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getPersona().getNombre() == null
				|| selectedColaborador.getDatosGenerales().getPersona().getNombre().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_PER_NOMBRE);
			ctrlMessage.setComponentZk(txtColabPersNombre);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getPersona().getApPaterno() == null
				|| selectedColaborador.getDatosGenerales().getPersona().getApPaterno().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_PER_AP_PAT);
			ctrlMessage.setComponentZk(txtColabPersApPat);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getPersona().getApMaterno() == null
				|| selectedColaborador.getDatosGenerales().getPersona().getApMaterno().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_PER_AP_MAT);
			ctrlMessage.setComponentZk(txtColabPersApMat);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getSexo() == null
				|| selectedColaborador.getDatosGenerales().getSexo().getIdSexo() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_SEXO);
			ctrlMessage.setComponentZk(comboboxColabDgSexo);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getEstadoCivil() == null
				|| selectedColaborador.getDatosGenerales().getEstadoCivil().getIdEstadoCivil() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EDO_CIVIL);
			ctrlMessage.setComponentZk(comboboxColabDgEdoCivil);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getRfc() == null
				|| selectedColaborador.getDatosGenerales().getRfc().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_RFC);
			ctrlMessage.setComponentZk(txtColabDgRfc);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getNss() == null
				|| selectedColaborador.getDatosGenerales().getNss().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_NSS);
			ctrlMessage.setComponentZk(txtColabDgNss);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getNss().length() < 11) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_NSS_LENGTH);
			ctrlMessage.setComponentZk(txtColabDgNss);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getCurp() == null
				|| selectedColaborador.getDatosGenerales().getCurp().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_CURP);
			ctrlMessage.setComponentZk(txtColabDgCurp);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getCurp().length() < 18) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_CURP_LENGTH);
			ctrlMessage.setComponentZk(txtColabDgCurp);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getDireccion().getCalle() == null
				|| selectedColaborador.getDatosGenerales().getDireccion().getCalle().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_CALLE);
			ctrlMessage.setComponentZk(txtColabDgDirCalle);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getDireccion().getNumeroExterior() == null
				|| selectedColaborador.getDatosGenerales().getDireccion().getNumeroExterior().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EXT);
			ctrlMessage.setComponentZk(txtColabDgDirNumExt);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getDireccion().getColonia() == null
				|| selectedColaborador.getDatosGenerales().getDireccion().getColonia().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_COL);
			ctrlMessage.setComponentZk(txtColabDgDirCol);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getDireccion().getCp() == null
				|| selectedColaborador.getDatosGenerales().getDireccion().getCp().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_COD);
			ctrlMessage.setComponentZk(txtColabDgDirCp);
			return ctrlMessage;
		}

		if (selectedPais == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_PAIS);
			ctrlMessage.setComponentZk(comboboxColabDgDirPais);
			return ctrlMessage;
		}

		if (selectedEstado == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_ESTADO);
			ctrlMessage.setComponentZk(comboboxColabDgDirEstado);
			return ctrlMessage;
		}

		if (selectedMunicipio == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_MUNUCIPIO);
			ctrlMessage.setComponentZk(comboboxColabDgDirMun);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getDireccion().getTelefono().getCasa() == null
				|| selectedColaborador.getDatosGenerales().getDireccion().getTelefono().getCasa().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_TEL_CASA);
			ctrlMessage.setComponentZk(txtColabDgDirTelCasa);
			return ctrlMessage;
		}

		if (selectedColaborador.getRol() == null || selectedColaborador.getRol().getIdRol() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_TIPO_EMPLEADO);
			ctrlMessage.setComponentZk(comboboxColabTipoEmpleado);
			return ctrlMessage;
		}

		if (selectedOficina == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_OFICINA);
			ctrlMessage.setComponentZk(comboboxColabOFicina);
			return ctrlMessage;
		}

		if (selectedColaborador.getEstatus() == null || selectedColaborador.getEstatus().getIdEstatus() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_ESTATUS);
			ctrlMessage.setComponentZk(comboboxColabEstatus);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getNombre() == null
				|| selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getNombre().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EMER_PER_NONM);
			ctrlMessage.setComponentZk(txtNombrePersEmerg);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getApPaterno() == null
				|| selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getApPaterno().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EMER_PER_AP_PAT);
			ctrlMessage.setComponentZk(txtApPatPersEmerg);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getApMaterno() == null
				|| selectedColaborador.getDatosGenerales().getEmergencia().getPersona().getApMaterno().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EMER_PER_AP_MAT);
			ctrlMessage.setComponentZk(txtApMatPersEmerg);
			return ctrlMessage;
		}

		if (selectedColaborador.getDatosGenerales().getEmergencia().getTelefono().getMovil() == null
				|| selectedColaborador.getDatosGenerales().getEmergencia().getTelefono().getMovil().isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_EMER_MOVIL);
			ctrlMessage.setComponentZk(txtMovilPersEmerg);
			return ctrlMessage;
		}

		if (selectedResponsable == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_RESPONSABLE);
			ctrlMessage.setComponentZk(comboResponsable);
			return ctrlMessage;
		}

		if (selectedColaborador.getModulosUsuario() == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_COLAB_ADD_MODULE);
			ctrlMessage.setComponentZk(gridPrivilege);
			return ctrlMessage;
		}
		return null;
	}


}
