package com.came.control.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sexo")
public class Sexo implements Serializable {

	private static final long serialVersionUID = -4962890869796184000L;

	private Long idSexo;
	private String nombre;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(Long idSexo) {
		this.idSexo = idSexo;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
