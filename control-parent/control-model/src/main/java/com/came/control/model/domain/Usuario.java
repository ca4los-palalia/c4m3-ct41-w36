package com.came.control.model.domain;

import java.io.Serializable;
import java.util.Date;
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

import org.zkoss.image.AImage;

import com.came.control.beans.funciones.CtrlUtils;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8668397144916161829L;
	

	private Long idUsuario;
	private String nip;
	private String imagen;
	private boolean activo;
	private String nombreUsuario;
	private String contrasenia;
	private String contraseniaRepetir;
	private String dekodifikatorUsr;
	private String dekodifikatorPass;
	private Rol rol;
	private DatosGenerales datosGenerales;
	private Estatus estatus;
	private Oficina oficina;
//	private Horario horario;
	private Organizacion organizacion;
	private String clave;
	private AImage imagenContent;
	private List<ModuloUsuario> modulosUsuario;
	private Date fechaContratacion;
	private Date fechaAlta;
	
	private List<Horario> horarios;
	private String btnHorarioSclass;
	private String btnHorarioLabel;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	@Column
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	@Column
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Column
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@OneToOne
	@JoinColumn(name = "rol")
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@OneToOne
	@JoinColumn(name = "datosGenerales")
	public DatosGenerales getDatosGenerales() {
		return datosGenerales;
	}

	public void setDatosGenerales(DatosGenerales datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	@OneToOne
	@JoinColumn(name = "estatus")
	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	@OneToOne
	@JoinColumn(name = "oficina")
	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

//	@OneToOne
//	@JoinColumn(name = "horario")
//	public Horario getHorario() {
//		return horario;
//	}
//
//	public void setHorario(Horario horario) {
//		this.horario = horario;
//	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Column
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column
	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	@Column
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	@Transient
	public AImage getImagenContent() {
		return imagen == null || imagen.isEmpty() ? null : new CtrlUtils().pathImagenToAimage(imagen);
	}

	public void setImagenContent(AImage imagenContent) {
		this.imagenContent = imagenContent;
	}

	@Transient
	public String getDekodifikatorUsr() {
		try {
			if(nombreUsuario != null && !nombreUsuario.isEmpty()) {
				dekodifikatorUsr = new CtrlUtils().Desencriptar(nombreUsuario);
			}
		} catch (Exception e) {}
		return dekodifikatorUsr;
	}

	public void setDekodifikatorUsr(String dekodifikatorUsr) {
		try {
			if(dekodifikatorUsr != null && !dekodifikatorUsr.isEmpty())
				nombreUsuario = new CtrlUtils().Encriptar(dekodifikatorUsr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dekodifikatorUsr = dekodifikatorUsr;
	}

	@Transient
	public String getDekodifikatorPass() {
		try {
			if(contrasenia != null && !contrasenia.isEmpty()) {
				dekodifikatorPass = new CtrlUtils().Desencriptar(contrasenia);
			}
		} catch (Exception e) {
			
		}
		return dekodifikatorPass;
	}

	public void setDekodifikatorPass(String dekodifikatorPass) {
		try {
			if(dekodifikatorPass != null && !dekodifikatorPass.isEmpty())
				contrasenia = new CtrlUtils().Encriptar(dekodifikatorPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dekodifikatorPass = dekodifikatorPass;
	}

	@Transient
	public List<ModuloUsuario> getModulosUsuario() {
		return modulosUsuario;
	}

	public void setModulosUsuario(List<ModuloUsuario> modulosUsuario) {
		this.modulosUsuario = modulosUsuario;
	}

	@Transient
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	@Transient
	public String getContraseniaRepetir() {
		return contraseniaRepetir;
	}

	public void setContraseniaRepetir(String contraseniaRepetir) {
		this.contraseniaRepetir = contraseniaRepetir;
	}

	@Transient
	public String getBtnHorarioSclass() {
		return btnHorarioSclass;
	}

	public void setBtnHorarioSclass(String btnHorarioSclass) {
		this.btnHorarioSclass = btnHorarioSclass;
	}

	@Transient
	public String getBtnHorarioLabel() {
		return btnHorarioLabel;
	}

	public void setBtnHorarioLabel(String btnHorarioLabel) {
		this.btnHorarioLabel = btnHorarioLabel;
	}

	
}


