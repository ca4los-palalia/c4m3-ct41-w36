package com.came.control.model.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.control.beans.ConfigureAccuracy;
import com.came.control.beans.ConfigureColor;
import com.came.control.beans.ConfigureHabilitarFuncion;
import com.came.control.beans.ConfigureTimer;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Municipio;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Pais;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.google.gson.Gson;

@Repository
public class IteratorList {

	public Estado getEstadoById(List<Estado> lista, Long idEstado) {
		Estado retornar = null;
		if (lista != null) {
			for (Estado item : lista) {
				if (idEstado.equals(item.getIdEstado())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Estado getEstadoByName(List<Estado> lista, String name) {
		Estado retornar = null;
		if (lista != null) {
			for (Estado item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Incidencia getIncidenciaById(List<Incidencia> lista, Long idFinder) {
		Incidencia retornar = null;
		if (lista != null) {
			for (Incidencia item : lista) {
				if (idFinder.equals(item.getIdIncidencia())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioById(List<Municipio> lista, Long idMunicipio) {
		Municipio retornar = null;
		if (lista != null) {
			for (Municipio item : lista) {
				if (idMunicipio.equals(item.getIdMunicipio())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioByName(List<Municipio> lista, String name) {
		Municipio retornar = null;
		if (lista != null) {
			for (Municipio item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisById(List<Pais> lista, Long idPais) {
		Pais retornar = null;
		if (lista != null) {
			for (Pais item : lista) {
				if (idPais.equals(item.getIdPais())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisByName(List<Pais> lista, String name) {
		Pais retornar = null;
		if (lista != null) {
			for (Pais item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Pais getPaisByClave(List<Pais> lista, String clave) {
		Pais retornar = null;
		if (lista != null) {
			for (Pais item : lista) {
				if (clave.equalsIgnoreCase(item.getClave())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Sexo getSexoById(List<Sexo> lista, Long idSexo) {
		Sexo retornar = null;
		if (lista != null) {
			for (Sexo item : lista) {
				if (idSexo.equals(item.getIdSexo())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public GrupoSanguineo getGrupoSanguineoById(List<GrupoSanguineo> lista, Long idFinder) {
		GrupoSanguineo retornar = null;
		if (lista != null) {
			for (GrupoSanguineo item : lista) {
				if (idFinder.equals(item.getIdGrupoSanguineo())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public EstadoCivil getEstadoCivilById(List<EstadoCivil> lista, Long idFinder) {
		EstadoCivil retornar = null;
		if (lista != null) {
			for (EstadoCivil item : lista) {
				if (idFinder.equals(item.getIdEstadoCivil())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Rol getRolById(List<Rol> lista, Long idFinder) {
		Rol retornar = null;
		if (lista != null) {
			for (Rol item : lista) {
				if (idFinder.equals(item.getIdRol())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Rol getRolByNombre(List<Rol> lista, String nameFinder) {
		Rol retornar = null;
		if (lista != null) {
			for (Rol item : lista) {
				if (nameFinder.equals(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Oficina getOficinaId(List<Oficina> lista, Long idFinder) {
		Oficina retornar = null;
		if (lista != null) {
			for (Oficina item : lista) {
				if (idFinder.equals(item.getIdOficina())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Estatus getEstatusId(List<Estatus> lista, Long idFinder) {
		Estatus retornar = null;
		if (lista != null) {
			for (Estatus item : lista) {
				if (idFinder.equals(item.getIdEstatus())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Usuario getUsuarioId(List<Usuario> lista, Long idFinder) {
		Usuario retornar = null;
		if (lista != null) {
			for (Usuario item : lista) {
				if (idFinder.equals(item.getIdUsuario())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public ZonaHorario getZonaHorarioById(List<ZonaHorario> lista, Long idFinder) {
		ZonaHorario retornar = null;
		if (lista != null) {
			for (ZonaHorario item : lista) {
				if (idFinder.equals(item.getIdZonaHorario())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public ZonaHorario getZonaHorarioByZh(List<ZonaHorario> lista, String zh) {
		ZonaHorario retornar = null;
		if (lista != null) {
			for (ZonaHorario item : lista) {
				if (zh.equals(item.getZonaHoraria())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	
	public List<Horario> crearHorarioTemporal(Usuario usr, ZonaHorario zh) {
		List<Horario> horarioTemplate = new ArrayList<Horario>();
		
		Horario horario = new Horario();
		horario.setDescanso(false);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.LUNES));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(false);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.MARTES));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(false);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.MIERCOLES));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(false);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.JUEVES));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(false);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.VIERNES));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(true);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.SABADO));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		
		horario = new Horario();
		horario.setDescanso(true);
		horario.setDia(ConstAtributos.DIAS.get(ConstAtributos.DOMINGO));
		horario.sethComidaEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR14));
		horario.sethComidaSalida(ConstAtributos.HORAS.get(ConstAtributos.HR15));
		horario.sethEntrada(ConstAtributos.HORAS.get(ConstAtributos.HR9));
		horario.sethSalida(ConstAtributos.HORAS.get(ConstAtributos.HR19));
		horario.setZonaHorario(zh);
		horario.setUsuario(usr);
		horarioTemplate.add(horario);
		return horarioTemplate;
	}

	
	public Configuracion crearColorThemeDefault(Organizacion org) {
		Configuracion cfg = new Configuracion();
		ConfigureColor color = new ConfigureColor();
		color.setColor1("#c850c0");
		color.setColor1Posicion(0);
		color.setColor2("#4158d0");
		color.setColor2Posicion(100);
		color.setDegradado(true);
		color.setOrientacion("to bottom left");
		color.setColorDegradado("background: linear-gradient(" + color.getOrientacion() + ", " + color.getColor1()+ " " + color.getColor1Posicion() + "%, " + color.getColor2() + " " + color.getColor2Posicion() + "%);");
		
		cfg = new Configuracion();
		cfg.setClave(ConstAtributos.CFG_COLOR_THEME);
		cfg.setValor(new Gson().toJson(color));
		cfg.setOrganizacion(org);
		return cfg;
	}
	
	
	public Configuracion crearAccuracyDefault(Organizacion org) {
		Configuracion cfg = new Configuracion();
		ConfigureAccuracy itemCfg = new ConfigureAccuracy();
		itemCfg.setMetros(20);
		cfg.setClave(ConstAtributos.CFG_ACCURACY);
		cfg.setValor(new Gson().toJson(itemCfg));
		cfg.setOrganizacion(org);
		return cfg;
	}
	
	public Configuracion crearActivacionDescripcionIncidenciasDefault(Organizacion org) {
		Configuracion cfg = new Configuracion();
		ConfigureHabilitarFuncion itemCfg = new ConfigureHabilitarFuncion();
		itemCfg.setActivar(true);
		itemCfg.setNombreFuncion("Activacion descripcion de incidencias en mensajes");
		cfg.setClave(ConstAtributos.CFG_MSG_INCIDENCIAS);
		cfg.setValor(new Gson().toJson(itemCfg));
		cfg.setOrganizacion(org);
		return cfg;
	}
	
	public Configuracion crearHoraDeCorteIncidenciasDefault(Organizacion org, Calendar cal) {
		Configuracion cfg = new Configuracion();
		ConfigureTimer itemCfg = new ConfigureTimer();
		itemCfg.setCaptureValue(cal.getTime());
		itemCfg.setHora(new CtrlUtils().convertirCalendarToString(cal, ConstAtributos.FORMAT_DATE_HH_MM));
		itemCfg.setNombre("Horario de corte de incidencias");
		
		cfg.setClave(ConstAtributos.CFG_TIME_INCIDENCIAS);
		cfg.setValor(new Gson().toJson(itemCfg));
		cfg.setOrganizacion(org);
		return cfg;
	}
	
	
	
	public Nacionalidad getNacionalidadById(List<Nacionalidad> lista, Long idFinder) {
		Nacionalidad retornar = null;
		if (lista != null) {
			for (Nacionalidad item : lista) {
				if (idFinder.equals(item.getIdNacionalidad())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Escolaridad getEscolaridadById(List<Escolaridad> lista, Long idFinder) {
		Escolaridad retornar = null;
		if (lista != null) {
			for (Escolaridad item : lista) {
				if (idFinder.equals(item.getIdEscolaridad())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public MetodoPago getMetodoPagoById(List<MetodoPago> lista, Long idFinder) {
		MetodoPago retornar = null;
		if (lista != null) {
			for (MetodoPago item : lista) {
				if (idFinder.equals(item.getIdMetodoPago())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Banco getBancoById(List<Banco> lista, Long idFinder) {
		Banco retornar = null;
		if (lista != null) {
			for (Banco item : lista) {
				if (idFinder.equals(item.getIdBanco())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
}
