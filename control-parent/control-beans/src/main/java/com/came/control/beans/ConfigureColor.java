package com.came.control.beans;

import java.io.Serializable;

public class ConfigureColor implements Serializable {

	private static final long serialVersionUID = -5661430389901545587L;

	private boolean degradado;
	private String color1;
	private String color2;
	private String colorDegradado;
	private Integer color1Posicion;
	private Integer color2Posicion;
	private String orientacion;

	public boolean isDegradado() {
		return degradado;
	}

	public void setDegradado(boolean degradado) {
		this.degradado = degradado;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColorDegradado() {
		return colorDegradado;
	}

	public void setColorDegradado(String colorDegradado) {
		this.colorDegradado = colorDegradado;
	}

	public Integer getColor1Posicion() {
		return color1Posicion;
	}

	public void setColor1Posicion(Integer color1Posicion) {
		this.color1Posicion = color1Posicion;
	}

	public Integer getColor2Posicion() {
		return color2Posicion;
	}

	public void setColor2Posicion(Integer color2Posicion) {
		this.color2Posicion = color2Posicion;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

}
