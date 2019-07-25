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
@Table(name = "politicas")
public class Politicas implements Serializable {

	private static final long serialVersionUID = 6163400515456129248L;
	
	private Long idPoliticas;
	private String descripcion;
	private Integer cantidadDeIncidencias;
	private Incidencia incidenciaOrigen;
	private Incidencia incidenciaFinal;
	private Periodo periodo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdPoliticas() {
		return idPoliticas;
	}

	public void setIdPoliticas(Long idPoliticas) {
		this.idPoliticas = idPoliticas;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public Integer getCantidadDeIncidencias() {
		return cantidadDeIncidencias;
	}

	public void setCantidadDeIncidencias(Integer cantidadDeIncidencias) {
		this.cantidadDeIncidencias = cantidadDeIncidencias;
	}

	@OneToOne
	@JoinColumn(name = "incidenciaOrigen")
	public Incidencia getIncidenciaOrigen() {
		return incidenciaOrigen;
	}

	public void setIncidenciaOrigen(Incidencia incidenciaOrigen) {
		this.incidenciaOrigen = incidenciaOrigen;
	}

	@OneToOne
	@JoinColumn(name = "incidenciaFinal")
	public Incidencia getIncidenciaFinal() {
		return incidenciaFinal;
	}

	public void setIncidenciaFinal(Incidencia incidenciaFinal) {
		this.incidenciaFinal = incidenciaFinal;
	}

	@OneToOne
	@JoinColumn(name = "periodo	")
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}
