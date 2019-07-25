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
@Table(name = "organizacion")
public class Organizacion implements Serializable {

	private static final long serialVersionUID = 5732954213212699211L;

	private Long idOrganizacion;
	private String rfc;
	private String razonSocial;
	private String logotipo;
	private String descripcion;
	private Direccion direccion;
	private Geolocalizacion geolocalizacion;
	private String email;
	private String web;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	@Column
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column
	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
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

	@OneToOne
	@JoinColumn(name = "geolocalizacion")
	public Geolocalizacion getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

}
