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

@Entity
@Table(name = "escolaridad")
public class Escolaridad implements Serializable {

	private static final long serialVersionUID = -3946484751609305007L;

	private Long idEscolaridad;
	private String nombre;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(Long idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	
}
