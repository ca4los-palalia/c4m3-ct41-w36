package com.came.control.web.bean;

import java.io.Serializable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Textbox;

import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

public class MessageControl implements Serializable {

	private static final long serialVersionUID = -485399847693487128L;
	//private Textbox txt;
	private Component componentZk;
	private String mensaje;
	private Integer idError;
	private Usuario usuario;
	private Organizacion organizacion;
	private String title;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getIdError() {
		return idError;
	}

	public void setIdError(Integer idError) {
		this.idError = idError;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Component getComponentZk() {
		return componentZk;
	}

	public void setComponentZk(Component txt) {
		this.componentZk = txt;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}
