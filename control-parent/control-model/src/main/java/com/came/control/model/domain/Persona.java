package com.came.control.model.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 8679999286789056092L;

	private Long idPersona;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private Date fechaNacimiento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	@Column
	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	@Column
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Transient
	public String getNombreCompleto() {
		String tmpNombre = nombre == null || nombre.isEmpty() ? "***" : nombre;
		String tmpApPa = apPaterno == null || apPaterno.isEmpty() ? "***" : apPaterno;
		String tmpApMa = apMaterno == null || apMaterno.isEmpty() ? "***" : apMaterno;
		return tmpNombre + " " + tmpApPa + " " + tmpApMa;
	}
}
