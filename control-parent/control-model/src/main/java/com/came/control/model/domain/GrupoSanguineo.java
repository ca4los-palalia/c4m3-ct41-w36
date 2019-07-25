package com.came.control.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupoSanguineo")
public class GrupoSanguineo implements Serializable {
	
	private static final long serialVersionUID = -4987524578441775834L;
	
	private Long idGrupoSanguineo;
	private String nombre;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdGrupoSanguineo() {
		return idGrupoSanguineo;
	}

	public void setIdGrupoSanguineo(Long idGrupoSanguineo) {
		this.idGrupoSanguineo = idGrupoSanguineo;
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

}
