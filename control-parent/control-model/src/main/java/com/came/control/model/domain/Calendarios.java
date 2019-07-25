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
@Table(name = "calendarios")
public class Calendarios implements Serializable {

	private static final long serialVersionUID = -5702165482162882812L;
	
	private Long idCalendarios;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCalendarios() {
		return idCalendarios;
	}

	public void setIdCalendarios(Long idCalendarios) {
		this.idCalendarios = idCalendarios;
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
