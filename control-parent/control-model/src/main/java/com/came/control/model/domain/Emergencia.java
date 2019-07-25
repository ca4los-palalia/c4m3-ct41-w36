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
@Table(name = "emergencia")
public class Emergencia implements Serializable {
	
	private static final long serialVersionUID = 1680708352668785996L;
	
	private Long idEmergencia;
	private Telefono telefono;
	private Persona persona;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdEmergencia() {
		return idEmergencia;
	}

	public void setIdEmergencia(Long idEmergencia) {
		this.idEmergencia = idEmergencia;
	}
	
	@OneToOne
	@JoinColumn(name = "telefono")
	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	

}
