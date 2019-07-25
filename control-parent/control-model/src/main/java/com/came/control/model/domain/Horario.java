package com.came.control.model.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

@Entity
@Table(name = "horario")
public class Horario implements Serializable {

	private static final long serialVersionUID = 2186998207567365452L;

	private Long idHorario;
	private String dia;
	private String hEntrada;
	private String hSalida;
	private String hComidaSalida;
	private String hComidaEntrada;
	private Boolean descanso;
	private PoliticaHorario politicaHorario;
	private ZonaHorario zonaHorario;
//	private Oficina oficina;
	private Usuario usuario;

	private Date hEntradaDate;
	private Date hSalidaDate;
	private Date hComidaSalidaDate;
	private Date hComidaEntradaDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}

	@Column
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@Column
	public String gethEntrada() {
		return hEntrada;
	}

	public void sethEntrada(String hEntrada) {
		this.hEntrada = hEntrada;
	}

	@Column
	public String gethSalida() {
		return hSalida;
	}

	public void sethSalida(String hSalida) {
		this.hSalida = hSalida;
	}

	@Column
	public String gethComidaSalida() {
		return hComidaSalida;
	}

	public void sethComidaSalida(String hComidaSalida) {
		this.hComidaSalida = hComidaSalida;
	}

	@Column
	public String gethComidaEntrada() {
		return hComidaEntrada;
	}

	public void sethComidaEntrada(String hComidaEntrada) {
		this.hComidaEntrada = hComidaEntrada;
	}

	@Column
	public Boolean getDescanso() {
		return descanso;
	}

	public void setDescanso(Boolean descanso) {
		this.descanso = descanso;
	}

	@OneToOne
	@JoinColumn(name = "politicaHorario")
	public PoliticaHorario getPoliticaHorario() {
		return politicaHorario;
	}

	public void setPoliticaHorario(PoliticaHorario politicaHorario) {
		this.politicaHorario = politicaHorario;
	}

	@OneToOne
	@JoinColumn(name = "zonaHorario")
	public ZonaHorario getZonaHorario() {
		return zonaHorario;
	}

	public void setZonaHorario(ZonaHorario zonaHorario) {
		this.zonaHorario = zonaHorario;
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
	public Date gethEntradaDate() {
		if (hEntrada != null && !hEntrada.isEmpty())
			hEntradaDate = timeStringToDate(hEntrada);
		return hEntradaDate;
	}

	public void sethEntradaDate(Date hEntradaDate) {
		hEntrada = dateToTimeString(hEntradaDate);
		this.hEntradaDate = hEntradaDate;
	}

	@Transient
	public Date gethSalidaDate() {
		if (hSalida != null && !hSalida.isEmpty())
			hSalidaDate = timeStringToDate(hSalida);
		return hSalidaDate;
	}

	public void sethSalidaDate(Date hSalidaDate) {
		hSalida = dateToTimeString(hSalidaDate);
		this.hSalidaDate = hSalidaDate;
	}

	@Transient
	public Date gethComidaSalidaDate() {
		if (hComidaSalida != null && !hComidaSalida.isEmpty())
			hComidaSalidaDate = timeStringToDate(hComidaSalida);
		return hComidaSalidaDate;
	}

	public void sethComidaSalidaDate(Date hComidaSalidaDate) {
		hComidaSalida = dateToTimeString(hComidaSalidaDate);
		this.hComidaSalidaDate = hComidaSalidaDate;
	}

	@Transient
	public Date gethComidaEntradaDate() {
		if (hComidaEntrada != null && !hComidaEntrada.isEmpty())
			hComidaEntradaDate = timeStringToDate(hComidaEntrada);
		return hComidaEntradaDate;
	}

	public void sethComidaEntradaDate(Date hComidaEntradaDate) {
		hComidaEntrada = dateToTimeString(hComidaEntradaDate);
		this.hComidaEntradaDate = hComidaEntradaDate;
	}

	private Date timeStringToDate(String timeString) {
		Date itemDate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			itemDate = sdf.parse(timeString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDate;
	}

	private String dateToTimeString(Date itemDate) {
		String timeString = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(itemDate);
		Integer hora = cal.get(Calendar.HOUR_OF_DAY);
		Integer min = cal.get(Calendar.MINUTE);
		String stringHr = hora.toString().length() == 1 ? 0 + "" + hora : hora.toString();
		String stringMin = min.toString().length() == 1 ? 0 + "" + min : min.toString();
		timeString = stringHr + ":" + stringMin;
		return timeString;
	}

	


	

}
