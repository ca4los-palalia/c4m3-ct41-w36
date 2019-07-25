package com.came.control.model.beans;

import java.io.Serializable;

import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Geolocalizacion;

public class RefactoringJs implements Serializable {

	private static final long serialVersionUID = 1881276196758880668L;
	
	private Direccion direccion;
	private Geolocalizacion geolocalizacion;
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Geolocalizacion getGeolocalizacion() {
		return geolocalizacion;
	}
	public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}
	
	
}
