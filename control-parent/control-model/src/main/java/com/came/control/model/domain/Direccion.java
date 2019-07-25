package com.came.control.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "direccion")
public class Direccion {

	private Long idDireccion;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String colonia;
	private String cp;
	private Estado estado;
	private Municipio municipio;
	private Telefono telefono;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	@Column
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column
	public String getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	@Column
	public String getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	@Column
	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Column
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@OneToOne
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
	@JoinColumn(name = "municipio")
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	@Transient
	public String getDirComplete() {
		String dir = (calle != null && !calle.isEmpty()) ? calle + ", " : "";
		dir += (numeroInterior != null && !numeroInterior.isEmpty()) ? "Int. " + numeroInterior + ", " : "";
		dir += (numeroExterior != null && !numeroExterior.isEmpty()) ? "Ext. " + numeroExterior + ", " : "";
		dir += (colonia != null && !colonia.isEmpty()) ? colonia + ", " : "";
		dir += estado != null ? estado.getNombre() + ", " : "";
		dir += municipio != null ? municipio.getNombre() + ", " : "";
		dir += (cp != null && !cp.isEmpty()) ? cp + ", " : "";
		if(!dir.isEmpty())
			dir = dir.substring(0, (dir.length() - 2) );
		return dir;
	}
}
