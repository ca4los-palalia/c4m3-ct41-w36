package com.came.control.beans;

import java.io.Serializable;
import java.util.Date;

public class ConfigureTimer implements Serializable {

	private static final long serialVersionUID = -3679218249483982552L;

	private String nombre;
	private String hora;
	private Date captureValue;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Date getCaptureValue() {
		return captureValue;
	}

	public void setCaptureValue(Date captureValue) {
		this.captureValue = captureValue;
	}

}
