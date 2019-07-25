package com.came.control.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "modulo")
public class Modulo implements Serializable {

	private static final long serialVersionUID = 8148736880041091900L;

	private Long idModulo;
	private String nombre;
	private String descripcion;
	private String ruta;
	private String icono;
	private String fechaActualizacion;
	private String subModulos;
	
	

	
	public Modulo() {
		
	}

	public Modulo(String nom, String descr, String ic) {
		nombre = nom;
		descripcion = descr;
		icono = ic;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
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
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Column
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(columnDefinition="TEXT")
	public String getSubModulos() {
		return subModulos;
	}

	public void setSubModulos(String subModulos) {
		this.subModulos = subModulos;
	}

		
	

}
