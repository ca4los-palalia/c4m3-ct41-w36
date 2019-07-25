package com.came.control.web.vm.metaclases;

import com.came.control.web.layer.LayerWebOperaciones;

public class HeaderMetaclas extends LayerWebOperaciones {

	private static final long serialVersionUID = 1828514702699662973L;

	private int totalTransacciones;

	public int getTotalTransacciones() {
		return totalTransacciones;
	}

	public void setTotalTransacciones(int totalTransacciones) {
		this.totalTransacciones = totalTransacciones;
	}

}
