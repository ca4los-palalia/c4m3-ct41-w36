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
@Table(name = "configuracion")
public class Configuracion implements Serializable {

	private static final long serialVersionUID = -139951522293481420L;

	private Long idConfiguracion;
	private String fechaActualizacion;
	private String clave;
	private String valor;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column
	/**
	 * @return valor.
	 * from JSONType
	 * administracion de color: ColorConfigure
	 * radio de geolocalizacion: ConfigureAccuracy
	 * habilitar funcion: ConfigureHabilitarFuncion
	 * configura hora de corte de incidencias: ConfigureTimer
	 * **/
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor.
	 * JSONType
	 * administracion de color: ColorConfigure
	 * radio de geolocalizacion: ConfigureAccuracy
	 * habilitar funcion: ConfigureHabilitarFuncion
	 * configura hora de corte de incidencias: ConfigureTimer
	 * **/
	public void setValor(String valor) {
		this.valor = valor;
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
