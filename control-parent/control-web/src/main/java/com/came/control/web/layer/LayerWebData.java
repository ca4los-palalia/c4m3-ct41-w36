package com.came.control.web.layer;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Textbox;

import com.came.control.beans.ConfigureColor;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.Municipio;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Pais;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;
import com.came.control.model.domain.ZonaHorario;

public class LayerWebData extends LayerWebServices {

	private static final long serialVersionUID = 5343779884540353448L;

	protected Organizacion organizacion;
	protected Usuario usuario;
	protected Modulo moduloSession;
	protected ConfigureColor colorTheme;

	protected Asistencia selectedAsistencia;
	protected Organizacion organizacionSelected;
	protected Estado selectedEstado;
	protected EstadoCivil selectedEstadoCivil;
	protected GrupoSanguineo selectedGrupoSanguineo;
	protected Incidencia incidenciaSelected;
	protected Municipio selectedMunicipio;
	protected Pais selectedPais;
	protected Sexo selectedSexo;
	protected Usuario selectedResponsable;
	protected Usuario selectedColaborador;
	protected UsuarioIncidencia selectedUsuarioIncidencia;
	protected Oficina selectedOficina;
	protected Rol rolSelected;
	protected ZonaHorario zonaHorarioSelected;

	@Wire("#mapMakerLocatorZul, #mapTransferTxt")
	protected Textbox mapTransferTxt;
	@Wire("#mapMakerLocatorZul, #finderAddress")
	protected Textbox finderAddress;
//	@Wire("#mapMakerLocatorZul, #geoLat")
//	protected Textbox geoLat;
//	@Wire("#mapMakerLocatorZul, #geoLong")
//	protected Textbox geoLong;

	@Wire("#mapMakerLocatorZul, #geoLat")
	protected Doublebox geoLat;
	@Wire("#mapMakerLocatorZul, #geoLong")
	protected Doublebox geoLong;

	protected List<Estado> estados;
	protected List<EstadoCivil> estadosCiviles;
	protected List<Estatus> estatusList;
	protected List<GrupoSanguineo> gruposSanguineos;
	protected List<Incidencia> incidencias;
	protected List<Municipio> municipios;
	protected List<Pais> paises;
	protected List<Sexo> sexos;
	protected List<Usuario> responsables;
	protected List<Nacionalidad> nacionalidades;
	protected List<Escolaridad> escolaridades;
	protected List<Banco> bancos;
	protected List<MetodoPago> metodosPago;
	protected List<Oficina> oficinas;
	protected List<Organizacion> organizaciones;
	protected List<Rol> roles;
	protected List<ZonaHorario> zonasHorarios;
	protected List<Asistencia> asistencias;
	protected List<UsuarioIncidencia> usuarioIncidencias;

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Modulo getModuloSession() {
		return moduloSession;
	}

	public void setModuloSession(Modulo moduloSession) {
		this.moduloSession = moduloSession;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<EstadoCivil> getEstadosCiviles() {
		return estadosCiviles;
	}

	public void setEstadosCiviles(List<EstadoCivil> estadosCiviles) {
		this.estadosCiviles = estadosCiviles;
	}

	public List<GrupoSanguineo> getGruposSanguineos() {
		return gruposSanguineos;
	}

	public void setGruposSanguineos(List<GrupoSanguineo> gruposSanguineos) {
		this.gruposSanguineos = gruposSanguineos;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}

	public Estado getSelectedEstado() {
		return selectedEstado;
	}

	public void setSelectedEstado(Estado selectedEstado) {
		this.selectedEstado = selectedEstado;
	}

	public EstadoCivil getSelectedEstadoCivil() {
		return selectedEstadoCivil;
	}

	public void setSelectedEstadoCivil(EstadoCivil selectedEstadoCivil) {
		this.selectedEstadoCivil = selectedEstadoCivil;
	}

	public GrupoSanguineo getSelectedGrupoSanguineo() {
		return selectedGrupoSanguineo;
	}

	public void setSelectedGrupoSanguineo(GrupoSanguineo selectedGrupoSanguineo) {
		this.selectedGrupoSanguineo = selectedGrupoSanguineo;
	}

	public Municipio getSelectedMunicipio() {
		return selectedMunicipio;
	}

	public void setSelectedMunicipio(Municipio selectedMunicipio) {
		this.selectedMunicipio = selectedMunicipio;
	}

	public Pais getSelectedPais() {
		return selectedPais;
	}

	public void setSelectedPais(Pais selectedPais) {
		this.selectedPais = selectedPais;
	}

	public Sexo getSelectedSexo() {
		return selectedSexo;
	}

	public void setSelectedSexo(Sexo selectedSexo) {
		this.selectedSexo = selectedSexo;
	}

	public List<Usuario> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<Usuario> responsables) {
		this.responsables = responsables;
	}

	public Usuario getSelectedResponsable() {
		return selectedResponsable;
	}

	public void setSelectedResponsable(Usuario selectedResponsable) {
		this.selectedResponsable = selectedResponsable;
	}

	public Usuario getSelectedColaborador() {
		return selectedColaborador;
	}

	public void setSelectedColaborador(Usuario selectedColaborador) {
		this.selectedColaborador = selectedColaborador;
	}

	public Oficina getSelectedOficina() {
		return selectedOficina;
	}

	public void setSelectedOficina(Oficina selectedOficina) {
		this.selectedOficina = selectedOficina;
	}

	public List<Oficina> getOficinas() {
		return oficinas;
	}

	public void setOficinas(List<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Estatus> getEstatusList() {
		return estatusList;
	}

	public void setEstatusList(List<Estatus> estatusList) {
		this.estatusList = estatusList;
	}

	public Organizacion getOrganizacionSelected() {
		return organizacionSelected;
	}

	public void setOrganizacionSelected(Organizacion organizacionSelected) {
		this.organizacionSelected = organizacionSelected;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public Rol getRolSelected() {
		return rolSelected;
	}

	public void setRolSelected(Rol rolSelected) {
		this.rolSelected = rolSelected;
	}

	public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia getIncidenciaSelected() {
		return incidenciaSelected;
	}

	public void setIncidenciaSelected(Incidencia incidenciaSelected) {
		this.incidenciaSelected = incidenciaSelected;
	}

	public ZonaHorario getZonaHorarioSelected() {
		return zonaHorarioSelected;
	}

	public void setZonaHorarioSelected(ZonaHorario zonaHorarioSelected) {
		this.zonaHorarioSelected = zonaHorarioSelected;
	}

	public List<ZonaHorario> getZonasHorarios() {
		return zonasHorarios;
	}

	public void setZonasHorarios(List<ZonaHorario> zonasHorarios) {
		this.zonasHorarios = zonasHorarios;
	}

	public Asistencia getSelectedAsistencia() {
		return selectedAsistencia;
	}

	public void setSelectedAsistencia(Asistencia selectedAsistencia) {
		this.selectedAsistencia = selectedAsistencia;
	}

	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public UsuarioIncidencia getSelectedUsuarioIncidencia() {
		return selectedUsuarioIncidencia;
	}

	public void setSelectedUsuarioIncidencia(UsuarioIncidencia selectedUsuarioIncidencia) {
		this.selectedUsuarioIncidencia = selectedUsuarioIncidencia;
	}

	public List<UsuarioIncidencia> getUsuarioIncidencias() {
		return usuarioIncidencias;
	}

	public void setUsuarioIncidencias(List<UsuarioIncidencia> usuarioIncidencias) {
		this.usuarioIncidencias = usuarioIncidencias;
	}

	public ConfigureColor getColorTheme() {
		return colorTheme;
	}

	public void setColorTheme(ConfigureColor colorTheme) {
		this.colorTheme = colorTheme;
	}

	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public List<Escolaridad> getEscolaridades() {
		return escolaridades;
	}

	public void setEscolaridades(List<Escolaridad> escolaridades) {
		this.escolaridades = escolaridades;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	public List<MetodoPago> getMetodosPago() {
		return metodosPago;
	}

	public void setMetodosPago(List<MetodoPago> metodosPago) {
		this.metodosPago = metodosPago;
	}

}
