package com.came.control.web.vm.metaclases;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Textbox;

import com.came.control.beans.CtrlRestService;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.constantes.ConstMensajes;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.web.bean.MessageControl;
import com.came.control.web.layer.LayerWebOperaciones;

public class LoginMetaclas extends LayerWebOperaciones {

	private static final long serialVersionUID = -4153307865411807121L;

//	@Wire("#backgroundMainPanel, #layoutMailLogin, #center1Login, #layout2Login, #center2Login, #layout3Login, #south1Login, #gridFormLogin, #txtLoginUsuario")
//	public Textbox txtLoginUsuario;
//	@Wire("#backgroundMainPanel, #layoutMailLogin, #center1Login, #layout2Login, #center2Login, #layout3Login, #south1Login, #gridFormLogin, #txtLoginPassword")
//	public Textbox txtLoginPassword;
	
	@Wire("#div01, #div02, #div03, #win01, #div08, #btnAutenticar")
	public Button btnAutenticar;
	@Wire("#div01, #div02, #div03, #win01, #div07, #txtPass")
	public Textbox txtPass;
	@Wire("#div01, #div02, #div03, #win01, #div06, #txtUsr")
	public Textbox txtUsr;
	@Wire("#div01, #div02, #div03, #win01, #div05, #txtOrg")
	public Textbox txtOrg;
	@Wire("#div01, #div02, #div03, #div04, #imagenProducto")
	public Image imagenProducto;
	
	private String user;
	private String password;
	private String rfc;
	public String modusPassword;
	public boolean verPassword;
	
	private MessageControl validarOrganizacion() {
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_ORG_TITLE);
		
		CtrlRestService restService = organizacionRest.getByRfc(rfc);
		if(!restService.isOk()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_ERROR);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_ORG_MESSAGE + "\n\n" + restService.getResponse());
			return ctrlMessage;
		}
		ctrlMessage.setOrganizacion((Organizacion) restService.getSingle());
		return ctrlMessage;
	}
	
	public MessageControl validarUsuario() {
		MessageControl ctrlMessage = new MessageControl();
		ctrlMessage.setTitle(ConstMensajes.VALIDA_USR_TITLE);
		if(rfc == null || rfc.isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_USR_RFC_EMPTY);
			ctrlMessage.setComponentZk(txtOrg);
			return ctrlMessage;
		}
		if(user == null || user.isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_USR_NOMBRE);
			ctrlMessage.setComponentZk(txtUsr);
			return ctrlMessage;
		}
		if(password == null || password.isEmpty()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje(ConstMensajes.VALIDA_USR_PSW);
			ctrlMessage.setComponentZk(txtPass);
			return ctrlMessage;
		}
		
		ctrlMessage = validarOrganizacion();
		if(ctrlMessage.getOrganizacion() == null) {
			if(ctrlMessage.getIdError() == null) {
				ctrlMessage.setMensaje(ConstMensajes.RFC + " <b>" + rfc + "</b> " + ConstMensajes.NO_REGISTRADO);
				ctrlMessage.setComponentZk(txtOrg);
				ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			}
			return ctrlMessage;
		}
		//Usuario usr = usuarioService.getAutenticacWithWeb(ctrlUtils.Encriptar(user), ctrlUtils.Encriptar(password), ctrlMessage.getOrganizacion());
		Usuario usr = usuarioRest.getAutenticaWithWeb(user, password, ctrlMessage.getOrganizacion());		
		if(usr == null) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje("El usuario no existe <b>" + user + "</b> no existe");
			ctrlMessage.setComponentZk(txtUsr);
			return ctrlMessage;
		}
		
		if(!usr.isActivo()) {
			ctrlMessage.setIdError(ConstAtributos.ESTATUS_WARNING);
			ctrlMessage.setMensaje("El usuario <b>" + user + "</b> esta <b>desactivado</b>");
			ctrlMessage.setComponentZk(txtUsr);
			return ctrlMessage;
		}
		
		ctrlMessage = new MessageControl();
		ctrlMessage.setUsuario(usr);
		return ctrlMessage;
	}
	
	
	
	
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}
	
	

	public void setPassword(String password) {
		this.password = password;
	}

	public AImage getMainImage() {
		return ctrlUtils.pathImagenToAimage(ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "companyProfileGif.gif");
//		return ConstAtributos.PATH_HOME + ConstAtributos.PATH_IMAGES_TEMPLATES + "companyProfileGif.gif";
	}
//	public AImage getAImage() {
//		AImage aimagen = null;
//		File file = null;
//		try {
//			file = new File(getClass().getClassLoader()
//					.getResource("layout/companyProfile.png").toURI());
//			aimagen = new AImage(file);
//		} catch (Exception e) {
//			System.out.println("El archivo no se encuentra en el sistema " + e.getMessage());
//		}
//		return aimagen;
//	}

	public String getModusPassword() {
		return modusPassword;
	}

	public void setModusPassword(String modusPassword) {
		this.modusPassword = modusPassword;
	}

	public boolean isVerPassword() {
		return verPassword;
	}

	public void setVerPassword(boolean verPassword) {
		this.verPassword = verPassword;
	}

	public String getRfc() {
		return rfc;
	}


	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	
}
