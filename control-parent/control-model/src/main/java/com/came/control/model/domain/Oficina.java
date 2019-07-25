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
@Table(name = "oficina")
public class Oficina implements Serializable {

	private static final long serialVersionUID = 5613984521131043656L;

	private Long idOficina;
	private String nombre;
	private String descripcion;
	private Direccion direccion;
	
	private Geolocalizacion geolocalizacion;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
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

	@OneToOne
	@JoinColumn(name = "direccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

//	@OneToOne
//	@JoinColumn(name = "horario")
//	public Horario getHorario() {
//		return horario;
//	}
//
//	public void setHorario(Horario horario) {
//		this.horario = horario;
//	}

	@OneToOne
	@JoinColumn(name = "geolocalizacion")
	public Geolocalizacion getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
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
