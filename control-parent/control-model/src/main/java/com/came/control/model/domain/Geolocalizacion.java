package com.came.control.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "geolocalizacion")
public class Geolocalizacion implements Serializable {

	private static final long serialVersionUID = -480385368376411663L;

	private Long idGeolocalizacion;
	private String latitud;
	private String longitud;
	private String descripcion;

	private String dirCalle;
	private String dirColonia;
	private String dirMunicipio;
	private String dirEstado;
	private String dirPais;
	private String dirCP;
	private String currentTimeClient;
	private Direccion dir;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdGeolocalizacion() {
		return idGeolocalizacion;
	}

	public void setIdGeolocalizacion(Long idGeolocalizacion) {
		this.idGeolocalizacion = idGeolocalizacion;
	}

	@Column
	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	@Column
	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getDirCalle() {
		return dirCalle;
	}

	public void setDirCalle(String dirCalle) {
		this.dirCalle = dirCalle;
	}

	@Column
	public String getDirColonia() {
		return dirColonia;
	}

	public void setDirColonia(String dirColonia) {
		this.dirColonia = dirColonia;
	}

	@Column
	public String getDirEstado() {
		return dirEstado;
	}

	public void setDirEstado(String dirEstado) {
		this.dirEstado = dirEstado;
	}

	@Column
	public String getDirCP() {
		return dirCP;
	}

	public void setDirCP(String dirCP) {
		this.dirCP = dirCP;
	}

	@Column
	public String getDirMunicipio() {
		return dirMunicipio;
	}

	public void setDirMunicipio(String dirMunicipio) {
		this.dirMunicipio = dirMunicipio;
	}

	@Column
	public String getDirPais() {
		return dirPais;
	}

	public void setDirPais(String dirPais) {
		this.dirPais = dirPais;
	}
	
	@Transient
	public String getCurrentTimeClient() {
		return currentTimeClient;
	}

	public void setCurrentTimeClient(String currentTimeClient) {
		this.currentTimeClient = currentTimeClient;
	}

	@Transient
	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}


	
}
