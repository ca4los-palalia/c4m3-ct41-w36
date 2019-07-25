package com.came.control.beans;

import java.io.Serializable;

public class ConfigureHabilitarFuncion implements Serializable {

	private static final long serialVersionUID = -3679218249483982552L;

	private String nombreFuncion;
	private boolean activar;

	public String getNombreFuncion() {
		return nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public boolean isActivar() {
		return activar;
	}

	public void setActivar(boolean activar) {
		this.activar = activar;
	}

}
