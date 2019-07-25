package com.came.control.model.domain;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "moduloUsuario")
public class ModuloUsuario implements Serializable {

	private static final long serialVersionUID = 5251584456310433341L;

	private Long idModuloUsuario;
	private Boolean activar;
	private String fechaActualizacion;
	private Modulo modulo;
	private List<Modulo> subModulo;
	private Usuario usuario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdModuloUsuario() {
		return idModuloUsuario;
	}

	public void setIdModuloUsuario(Long idModuloUsuario) {
		this.idModuloUsuario = idModuloUsuario;
	}

	@Column
	public Boolean getActivar() {
		return activar;
	}

	public void setActivar(Boolean activar) {
		this.activar = activar;
	}

	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToOne
	@JoinColumn(name = "modulo")
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Transient
	public List<Modulo> getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(List<Modulo> subModulo) {
		this.subModulo = subModulo;
	}

	
}
