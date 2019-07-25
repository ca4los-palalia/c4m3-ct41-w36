package com.came.control.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.came.control.model.domain.Incidencia;

public class DiaCapturado implements Serializable {

	private static final long serialVersionUID = -5423422170138842495L;

	private Incidencia incidencia;
	private Integer hrs;
	private String tagDay;
	private String observacion;
	private Integer diaFecha;
	private String diaNombre;
	private Calendar fechaCalendar;
	private Date fechaDate;
	private boolean lunesFinded;
	private boolean vmDisabled;

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Integer getHrs() {
		return hrs;
	}

	public void setHrs(Integer hrs) {
		this.hrs = hrs;
	}

	public String getTagDay() {
		return tagDay;
	}

	public void setTagDay(String tagDay) {
		this.tagDay = tagDay;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getDiaFecha() {
		return diaFecha;
	}

	public void setDiaFecha(Integer diaFecha) {
		this.diaFecha = diaFecha;
	}

	public Calendar getFechaCalendar() {
		return fechaCalendar;
	}

	public void setFechaCalendar(Calendar fechaCalendar) {
		this.fechaCalendar = fechaCalendar;
	}

	public String getDiaNombre() {
		return diaNombre;
	}

	public void setDiaNombre(String diaNombre) {
		this.diaNombre = diaNombre;
	}

	public boolean isLunesFinded() {
		return lunesFinded;
	}

	public void setLunesFinded(boolean lunesFinded) {
		this.lunesFinded = lunesFinded;
	}

	public boolean isVmDisabled() {
		return vmDisabled;
	}

	public void setVmDisabled(boolean vmDisabled) {
		this.vmDisabled = vmDisabled;
	}

	public Date getFechaDate() {
		return fechaDate;
	}

	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}

	
}
