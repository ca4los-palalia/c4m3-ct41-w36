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
@Table(name = "usuarioIncidencia")
public class UsuarioIncidencia implements Serializable {

	private static final long serialVersionUID = 8668397144916161829L;

	private Long idUsuarioIncidencia;
	private String fecha;
	private Usuario usuario;
	private Incidencia incidencia;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdUsuarioIncidencia() {
		return idUsuarioIncidencia;
	}

	public void setIdUsuarioIncidencia(Long idUsuarioIncidencia) {
		this.idUsuarioIncidencia = idUsuarioIncidencia;
	}

	@Column
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne
	@JoinColumn(name = "incidencia")
	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
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
