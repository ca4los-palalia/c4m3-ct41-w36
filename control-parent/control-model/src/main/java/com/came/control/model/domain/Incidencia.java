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
@Table(name = "incidencia")
public class Incidencia implements Serializable {

	private static final long serialVersionUID = 1081759638186348941L;

	private Long idIncidencia;
	private String nombre;
	private String descripcion;
	private String clave;
	private Integer descuentoPorcentaje;
	private String color;
	private String style;
	private Organizacion organizacion;

//	private Boolean requiereVoBo;
//	private Boolean requiereAutorizacion;
//	private Boolean requiereDocumentacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column
	public Integer getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(Integer descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Transient
	public String getStyle() {
		return color != null && !color.isEmpty() ? "background-color: " + color + ";" : "";
		//return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	@Transient
	public String getConcater() {
		String stringReturn = (clave != null ? "(" + clave + ") " : "");
		stringReturn += (nombre != null ? nombre : "");
		return stringReturn.trim();
	}
}
