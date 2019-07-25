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
@Table(name = "zonaHorario")
public class ZonaHorario implements Serializable {

	private static final long serialVersionUID = 2576175050384568867L;

	private Long idZonaHorario;
	private String utc;
	private String zonaHoraria;
	private String pais;
	private String principalesPaises;
	private Organizacion organizacion; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdZonaHorario() {
		return idZonaHorario;
	}

	public void setIdZonaHorario(Long idZonaHorario) {
		this.idZonaHorario = idZonaHorario;
	}

	@Column
	public String getUtc() {
		return utc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}

	@Column
	public String getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	@Column
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(columnDefinition = "TEXT")
	public String getPrincipalesPaises() {
		return principalesPaises;
	}

	public void setPrincipalesPaises(String principalesPaises) {
		this.principalesPaises = principalesPaises;
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
