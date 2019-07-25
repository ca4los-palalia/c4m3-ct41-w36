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
@Table(name = "politicaHorario")
public class PoliticaHorario implements Serializable {

	private static final long serialVersionUID = -2712128009015133985L;
	
	private Long idPoliticaHorario;
	private Boolean activar;
	private String nombre;
	private String descripcion;
	private Integer valor;
	private Organizacion organizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdPoliticaHorario() {
		return idPoliticaHorario;
	}

	public void setIdPoliticaHorario(Long idPoliticaHorario) {
		this.idPoliticaHorario = idPoliticaHorario;
	}

	@Column
	public Boolean getActivar() {
		return activar;
	}

	public void setActivar(Boolean activar) {
		this.activar = activar;
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

	@Column
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
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
