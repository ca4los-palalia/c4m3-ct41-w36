package com.came.control.model.beans;

import java.io.Serializable;
import java.util.Date;

import com.came.control.model.domain.Usuario;

public class CapturadorManualAsistencia implements Serializable {

	private static final long serialVersionUID = -5472269909571235331L;

	private Usuario usuario;
	private Date fechaActualizacion;
	private Date fechaRegistro;
	private DiaCapturado lunes;
	private DiaCapturado martes;
	private DiaCapturado miercoles;
	private DiaCapturado jueves;
	private DiaCapturado viernes;
	private DiaCapturado sabado;
	private DiaCapturado domingo;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public DiaCapturado getLunes() {
		return lunes;
	}

	public void setLunes(DiaCapturado lunes) {
		this.lunes = lunes;
	}

	public DiaCapturado getMartes() {
		return martes;
	}

	public void setMartes(DiaCapturado martes) {
		this.martes = martes;
	}

	public DiaCapturado getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(DiaCapturado miercoles) {
		this.miercoles = miercoles;
	}

	public DiaCapturado getJueves() {
		return jueves;
	}

	public void setJueves(DiaCapturado jueves) {
		this.jueves = jueves;
	}

	public DiaCapturado getViernes() {
		return viernes;
	}

	public void setViernes(DiaCapturado viernes) {
		this.viernes = viernes;
	}

	public DiaCapturado getSabado() {
		return sabado;
	}

	public void setSabado(DiaCapturado sabado) {
		this.sabado = sabado;
	}

	public DiaCapturado getDomingo() {
		return domingo;
	}

	public void setDomingo(DiaCapturado domingo) {
		this.domingo = domingo;
	}

}
