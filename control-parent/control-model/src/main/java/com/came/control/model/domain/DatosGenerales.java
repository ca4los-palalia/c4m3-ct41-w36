package com.came.control.model.domain;

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
@Table(name = "datosGenerales")
public class DatosGenerales implements Serializable {

	private static final long serialVersionUID = -8324511586054853000L;

	private Long idDatosGenerales;
	private Usuario responsable;
	private String correo;
	private String nss;
	private String rfc;
	private String curp;
	private Sexo sexo;
	private GrupoSanguineo grupoSanguineo;;
	private EstadoCivil estadoCivil;
	private Persona persona;
	private Direccion direccion;
	private Emergencia emergencia;
	private Organizacion organizacion;
	
	private Estado lugarNacimiento;
	private Nacionalidad nacionalidad;
	private Escolaridad escolaridad;
	
	private String nombreDelPadre;
	private String nombreDeLaMadre;
	private String nombreDeLaPareja;
	private Boolean tieneHijos;
	private String unidadMedica;
	private MetodoPago metodoPago;
	private Banco banco;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdDatosGenerales() {
		return idDatosGenerales;
	}

	public void setIdDatosGenerales(Long idDatosGenerales) {
		this.idDatosGenerales = idDatosGenerales;
	}

	@OneToOne
	@JoinColumn(name = "responsable")
	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	@Column
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column
	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	@Column
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column
	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	@OneToOne
	@JoinColumn(name = "sexo")
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@OneToOne
	@JoinColumn(name = "grupoSanguineo")
	public GrupoSanguineo getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	@OneToOne
	@JoinColumn(name = "estadoCivil")
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@OneToOne
	@JoinColumn(name = "persona")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@OneToOne
	@JoinColumn(name = "direccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@OneToOne
	@JoinColumn(name = "emergencia")
	public Emergencia getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(Emergencia emergencia) {
		this.emergencia = emergencia;
	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	
	@OneToOne
	@JoinColumn(name = "lugarNacimiento")
	public Estado getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(Estado lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	
	@OneToOne
	@JoinColumn(name = "nacionalidad")
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@OneToOne
	@JoinColumn(name = "escolaridad")
	public Escolaridad getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(Escolaridad escolaridad) {
		this.escolaridad = escolaridad;
	}
	
	@Column
	public String getNombreDelPadre() {
		return nombreDelPadre;
	}

	public void setNombreDelPadre(String nombreDelPadre) {
		this.nombreDelPadre = nombreDelPadre;
	}

	@Column
	public String getNombreDeLaMadre() {
		return nombreDeLaMadre;
	}

	public void setNombreDeLaMadre(String nombreDeLaMadre) {
		this.nombreDeLaMadre = nombreDeLaMadre;
	}

	@Column
	public String getNombreDeLaPareja() {
		return nombreDeLaPareja;
	}

	public void setNombreDeLaPareja(String nombreDeLaPareja) {
		this.nombreDeLaPareja = nombreDeLaPareja;
	}

	@Column
	public Boolean getTieneHijos() {
		return tieneHijos == null ? false : tieneHijos;
	}

	public void setTieneHijos(Boolean tieneHijos) {
		this.tieneHijos = tieneHijos;
	}

	@Column
	public String getUnidadMedica() {
		return unidadMedica;
	}

	public void setUnidadMedica(String unidadMedica) {
		this.unidadMedica = unidadMedica;
	}

	@OneToOne
	@JoinColumn(name = "metodoPago")
	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	@OneToOne
	@JoinColumn(name = "banco")
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	
}
