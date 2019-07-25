package com.came.control.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "asistencia")
public class Asistencia implements Serializable {

	private static final long serialVersionUID = -7501672446099115116L;

	private Long idAsistencia;
	private String fechaActualizacion;
	private Incidencia incidencias;
	private String registro;
	private Usuario usuario;
	private Integer hrsTrabajo;
	private String observaciones;
	private String registroDias;
	private Organizacion organizacion;
	private boolean finalizado;
	private boolean visibleButton;
	private boolean colapsed;
	private int span;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(Long idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToOne
	@JoinColumn(name = "incidencias")
	public Incidencia getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Incidencia incidencias) {
		this.incidencias = incidencias;
	}

	@Column(columnDefinition = "TEXT")
	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Column
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	@Column
	public Integer getHrsTrabajo() {
		return hrsTrabajo;
	}

	public void setHrsTrabajo(Integer hrsTrabajo) {
		this.hrsTrabajo = hrsTrabajo;
	}

	@Column
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Transient
	public boolean isVisibleButton() {
		return !visibleButton;
	}

	public void setVisibleButton(boolean visibleButton) {
		this.visibleButton = !visibleButton;
	}

	@Transient
	public boolean isColapsed() {
		return colapsed;
	}

	public void setColapsed(boolean colapsed) {
		this.colapsed = colapsed;
	}

	@Transient
	public int getSpan() {
		return span;
	}

	public void setSpan(int span) {
		this.span = span;
	}

	public String getRegistroDias() {
		return registroDias;
	}

	public void setRegistroDias(String registroDias) {
		this.registroDias = registroDias;
	}
	
	

}
