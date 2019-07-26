package com.came.stock.model.domain;

import java.io.Serializable;
import java.util.Calendar;
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
public class OrdenCompra implements Serializable {
	
	private static final long serialVersionUID = 1308611252913327251L;
	
	private Long idOrdenCompra;
	private String codigo;
	private Calendar fechaOrden;
	private Cotizacion cotizacion;
	private EstatusRequisicion estatusRequisicion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String cancelarDescripcion;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrdenCompra() {
		return this.idOrdenCompra;
	}

	public void setIdOrdenCompra(Long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	@Column
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column
	public Calendar getFechaOrden() {
		return this.fechaOrden;
	}

	public void setFechaOrden(Calendar fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	@OneToOne
	@JoinColumn(name = "idCotizacion")
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return this.estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	@Column
	public String getCancelarDescripcion() {
		return this.cancelarDescripcion;
	}

	public void setCancelarDescripcion(String cancelarDescripcion) {
		this.cancelarDescripcion = cancelarDescripcion;
	}
}
