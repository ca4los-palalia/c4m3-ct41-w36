package com.came.stock.model.domain;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.came.stock.utilidades.StockUtils;

@Entity
@Table
public class Contrato {
	private Long idContrato;
	private String nombre;
	private Long diasPago;
	private Calendar fecha;
	private Calendar fechaVigenciaFin;
	private Calendar fechaVigenciaInicio;
	private Date fechaVigenciaFinDate;
	private Date fechaVigenciaInicioDate;
	private String descripcion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdContrato() {
		return this.idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	@Column(name = "diaspago")
	public Long getDiasPago() {
		return this.diasPago;
	}

	public void setDiasPago(Long diasPago) {
		this.diasPago = diasPago;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaVigenciaFin")
	public Calendar getFechaVigenciaFin() {
		return this.fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Calendar fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaVigenciaInicio")
	public Calendar getFechaVigenciaInicio() {
		return this.fechaVigenciaInicio;
	}

	public void setFechaVigenciaInicio(Calendar fechaVigenciaInicio) {
		this.fechaVigenciaInicio = fechaVigenciaInicio;
	}

	@Column
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
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

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Transient
	public Date getFechaVigenciaFinDate() {
		if (this.fechaVigenciaFin != null) {
			this.fechaVigenciaFinDate = new StockUtils().convertirCalendarToDate(this.fechaVigenciaFin);
		}
		return this.fechaVigenciaFinDate;
	}

	public void setFechaVigenciaFinDate(Date fechaVigenciaFinDate) {
		if (fechaVigenciaFinDate != null) {
			this.fechaVigenciaFin = new StockUtils().convertirDateToCalendar(fechaVigenciaFinDate);
		}
		this.fechaVigenciaFinDate = fechaVigenciaFinDate;
	}

	@Transient
	public Date getFechaVigenciaInicioDate() {
		if (this.fechaVigenciaInicio != null) {
			this.fechaVigenciaInicioDate = new StockUtils().convertirCalendarToDate(this.fechaVigenciaInicio);
		}
		return this.fechaVigenciaInicioDate;
	}

	public void setFechaVigenciaInicioDate(Date fechaVigenciaInicioDate) {
		if (fechaVigenciaInicioDate != null) {
			this.fechaVigenciaInicio = new StockUtils().convertirDateToCalendar(fechaVigenciaInicioDate);
		}
		this.fechaVigenciaInicioDate = fechaVigenciaInicioDate;
	}
}
