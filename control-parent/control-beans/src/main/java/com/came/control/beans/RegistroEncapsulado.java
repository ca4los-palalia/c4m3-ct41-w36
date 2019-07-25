package com.came.control.beans;

import java.io.Serializable;

public class RegistroEncapsulado implements Serializable {

	private static final long serialVersionUID = 6332825892946450989L;

	private Registro jornadaEntrada;
	private Registro jornadaSalida;
	private Registro comidaEntrada;
	private Registro comidaSalida;

	public Registro getJornadaEntrada() {
		return jornadaEntrada;
	}

	public void setJornadaEntrada(Registro jornadaEntrada) {
		this.jornadaEntrada = jornadaEntrada;
	}

	public Registro getJornadaSalida() {
		return jornadaSalida;
	}

	public void setJornadaSalida(Registro jornadaSalida) {
		this.jornadaSalida = jornadaSalida;
	}

	public Registro getComidaEntrada() {
		return comidaEntrada;
	}

	public void setComidaEntrada(Registro comidaEntrada) {
		this.comidaEntrada = comidaEntrada;
	}

	public Registro getComidaSalida() {
		return comidaSalida;
	}

	public void setComidaSalida(Registro comidaSalida) {
		this.comidaSalida = comidaSalida;
	}

}
