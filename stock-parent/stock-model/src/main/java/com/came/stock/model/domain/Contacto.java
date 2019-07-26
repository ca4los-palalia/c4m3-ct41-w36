package com.came.stock.model.domain;

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
@Table
public class Contacto implements Serializable {
	private static final long serialVersionUID = -7065704145995755117L;
	private Long idContacto;
	private Telefono telefono;
	private Email email;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdContacto() {
		return this.idContacto;
	}

	public void setIdContacto(Long idContacto) {
		this.idContacto = idContacto;
	}

	
	@OneToOne
	@JoinColumn(name = "telefono")
	public Telefono getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@OneToOne
	@JoinColumn(name = "email")
	public Email getEmail() {
		return this.email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "telefono")
	public Telefono getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	public Email getEmail() {
		return this.email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	*/
}
