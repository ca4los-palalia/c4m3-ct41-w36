package com.came.control.beans;

import java.io.Serializable;

public class ConfigureAccuracy implements Serializable {

	private static final long serialVersionUID = -3679218249483982552L;

	private Integer metros;
	private boolean habilitarValidacion;
	private boolean disabledTextbox;

	public Integer getMetros() {
		return metros;
	}

	public void setMetros(Integer metros) {
		this.metros = metros;
	}

	public boolean isHabilitarValidacion() {
		return habilitarValidacion;
	}

	public void setHabilitarValidacion(boolean habilitarValidacion) {
		this.habilitarValidacion = habilitarValidacion;
	}

	public boolean isDisabledTextbox() {
		return disabledTextbox;
	}

	public void setDisabledTextbox(boolean disabledTextbox) {
		this.disabledTextbox = disabledTextbox;
	}

}
