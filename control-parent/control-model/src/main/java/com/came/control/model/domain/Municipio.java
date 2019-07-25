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
@Table(name = "municipio")
public class Municipio implements Serializable {

	private static final long serialVersionUID = 8437759530064917862L;

	Long idMunicipio;
	String nombre;
	Estado estado;
	String numeroMunicipio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Column
	public String getNumeroMunicipio() {
		return numeroMunicipio;
	}

	public void setNumeroMunicipio(String numeroMunicipio) {
		this.numeroMunicipio = numeroMunicipio;
	}

}
