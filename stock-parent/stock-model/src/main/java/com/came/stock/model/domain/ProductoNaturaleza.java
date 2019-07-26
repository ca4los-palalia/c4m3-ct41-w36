package com.came.stock.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ProductoNaturaleza {
	private Long idProductoNaturaleza;
	private String nombre;
	private String simbolo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoNaturaleza() {
		return this.idProductoNaturaleza;
	}

	public void setIdProductoNaturaleza(Long idProductoNaturaleza) {
		this.idProductoNaturaleza = idProductoNaturaleza;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
}
