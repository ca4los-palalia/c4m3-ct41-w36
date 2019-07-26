package com.came.control.beans.constantes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConstAtributos {

	public final static String PATH_HOME = System.getProperty("user.home") + System.getProperty("file.separator")
			+ "came-control" + System.getProperty("file.separator");
	public final static String PATH_CFG = "cfg" + System.getProperty("file.separator");
	public final static String PATH_LAYOUT = "layout" + System.getProperty("file.separator");
	public final static String PATH_IMAGES_ESTADOS = "imagenes" + System.getProperty("file.separator") + "estados"
			+ System.getProperty("file.separator");
	public final static String PATH_IMAGES_ORG = "imagenes" + System.getProperty("file.separator") + "organizaciones"
			+ System.getProperty("file.separator");
	public final static String PATH_IMAGES_PAISES = "imagenes" + System.getProperty("file.separator") + "paises"
			+ System.getProperty("file.separator");
	public final static String PATH_IMAGES_USUARIOS = "imagenes" + System.getProperty("file.separator") + "usuarios"
			+ System.getProperty("file.separator");
	public final static String PATH_IMAGES_TEMPLATES = "imagenes" + System.getProperty("file.separator") + "templates"
			+ System.getProperty("file.separator");

	public final static String CONTEXT_WEB = "Contexto web";

	public final static String ERROR_EXCEPTION = "Exception";

	public static final String FORMAT_DATE_YYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT_DATE_YYY_MM_DDTHH_MM_SSSZ = "yyyy-MM-ddTHH:mm:sssZ";

	public static final String FORMAT_DATE_HH_MM = "HH:mm";
	public static final Object BENUTZER = "BENUTZER";
	public final static String CONTRASENIA = "contrasenia";
	public final static String CURP = "curp";
	public final static String DIRECCION = "direccion";
	public final static String ESTADO_CIVIL = "estadoCivil";
	public final static String ESTATUS = "estatus";
	public final static String ID_FINDER = "idFinder";
	public final static String FINALIZADO = "finalizadoEstado";
	public final static String NIP = "nip";
	public final static String NOMBRE = "nnombre";
	public final static String CLAVE = "clave";
	public final static String NOMBRE_USUARIO = "nombreUsuario";
	public final static String NSS = "nss";
	public final static String OFICINA = "oficina";
	public final static String ORGANIZACION = "organizacion";
	public final static String USUARIO = "usuario";
	public final static String INCIDENCIA = "incidencia";
	public final static String FECHA = "fecha";
	public final static String FECHA_FIN = "fechaFin";
	public final static String PERSONA = "persona";
	public final static String RFC = "rfc";
	public final static String ROL = "rol";
	public final static String HOMBRE = "Hombre";
	public final static String ROL_SYSADMIN = "sysadmin";
	public final static String ROL_ADMIN = "admin";
	public final static String ROL_GERENTE = "gerente";
	public final static String MODULO_CHECADOR = "Checador";
	public final static String MODULO_CATALOGOS = "Catalogos";
	public final static String MODULO_CONFIGURACION = "Configuracion";
	public final static String MODULO_TRABAJADORES = "Trabajadores";
	public final static String ZONA_HORARIA = "zonaHoraria";

	public final static String PUEBLA = "Puebla";
	public final static String MX = "MX";

	public final static String MAP_ORG_RAZON_SOCIAL = "orgRazonSocial";
	public final static String MAP_ORG_DESC = "orgDescripcion";
	public final static String MAP_ORG_RFC = "orgRfc";
	public final static String MAP_ORG_LOGOTIPO = "orgLogotipo";
	public final static String MAP_ORG_DIR_CALLE = "orgDirCalle";
	public final static String MAP_ORG_DIR_COLONIA = "orgDirColonia";
	public final static String MAP_ORG_DIR_CP = "orgDirCp";
	public final static String MAP_ORG_DIR_ESTADO = "orgDirEstado";
	public final static String MAP_ORG_DIR_NUM_INT = "orgDirNumInt";
	public final static String MAP_ORG_DIR_NUM_EXT = "orgDirNumExt";
	public final static String MAP_ORG_GEO_LATITUD = "orgGeoLatitud";
	public final static String MAP_ORG_GEO_LONGITUD = "orgGeoLongitud";
	public final static String MAP_ORG_GEO_DESCRIPCION = "orgGeoDescripcion";

	public final static String MAP_USR_ACTIVO = "usrActivo";
	public final static String MAP_USR_NOMBRE = "usrNombre";
	public final static String MAP_USR_PASS = "usrPass";
	public final static String MAP_USR_NIP = "usrNip";
	public final static String MAP_USR_IMAGEN = "usrImagen";
	public final static String MAP_USR_EMAIL = "usrEmail";
	public final static String MAP_USR_CURP = "usrCurp";
	public final static String MAP_USR_NSS = "usrNss";
	public final static String MAP_USR_RFC = "usrRfc";
	public final static String MAP_USR_EDO_CIVIL = "usrEdoCivil";
	public final static String MAP_USR_GPO_SANGUINEO = "usrGpoSanguineo";
	public final static String MAP_USR_DIR_CALLE = "usrDirCalle";
	public final static String MAP_USR_DIR_COLONIA = "usrDirColonia";
	public final static String MAP_USR_DIR_CP = "usrDirCp";
	public final static String MAP_USR_DIR_ESTADO = "usrDirEstado";
	public final static String MAP_USR_DIR_NUM_EXT = "usrDirNumExt";
	public final static String MAP_USR_DIR_NUM_INT = "usrDirNumInt";
	public final static String MAP_USR_TEL_CASA = "usrTelCasa";
	public final static String MAP_USR_TEL_MOVIL = "usrTelMovil";
	public final static String MAP_USR_TEL_OFICINA = "usrTelOficina";
	public final static String MAP_USR_PERSONA_NOMBRE = "usrPersonaNombre";
	public final static String MAP_USR_PERSONA_APPATERNO = "usrPersonaApPaterno";
	public final static String MAP_USR_PERSONA_APMATERNO = "usrPersonaApMaterno";
	public final static String MAP_USR_SEXO = "usrSexo";
	public final static String MAP_USR_RESPONSABLE = "usrResponsable";
	public final static String MAP_USR_ROL = "usrRol";
	public final static String MAP_USR_ESTATUS = "usrEstatus";
	public final static String MAP_USR_HORARIO = "usrHorario";
	public final static String MAP_USR_OFICINA = "usrOficina";
	public final static String MAP_EMERGENCIA_PERSONA_NOMBRE = "emergenciaPersonaNombre";
	public final static String MAP_EMERGENCIA_PERSONA_AP_PATERNO = "emergenciaPersonaApPaterno";
	public final static String MAP_EMERGENCIA_PERSONA_AP_MATERNO = "emergenciaPersonaApMaterno";
	public final static String MAP_EMERGENCIA_TEL_CASA = "emergenciaTelCasa";
	public final static String MAP_EMERGENCIA_TEL_MOVIL = "emergenciaTelMovil";
//	public final static String MAP_RESPONSABLE_PERSONA_NOMBRE = "responsablePersonaNombre";
//	public final static String MAP_RESPONSABLE_PERSONA_APPATERNO = "MAP_responsablePersonaApPaterno";
//	public final static String MAP_RESPONSABLE_PERSONA_APMATERNO = "responsablePersonaApMaterno";
	public final static String MAP_ORGANIZACION = "organizacion";
	
	
	
	public final static String INCIDENCIA_FR = "FR";
	public final static String INCIDENCIA_F = "F";
	public final static String INCIDENCIA_CT = "CT";
	public final static String INCIDENCIA_CF = "CF";

	public final static String SESSION_FIRMA = "session_firma";
	public final static String SESSION_USUARIO = "session_usuario";
	public final static String SESSION_MODULO_USUARIO = "session_modulo_usuario";
	public final static String SESSION_COLOR_CONFIGURE = "session_color_configure";

	public final static String CFG_COLOR_THEME = "COLOR_THEME";
	public final static String CFG_ACCURACY = "ACCURACY";
	public final static String CFG_MSG_INCIDENCIAS = "MSG_INCIDENCIAS";
	public final static String CFG_TIME_INCIDENCIAS = "TIME_INCIDENCIAS";

	
	
	
	
	public final static Integer ESTATUS_INFORMATION = 0;
	public final static Integer ESTATUS_ERROR = 1;
	public final static Integer ESTATUS_WARNING = 2;
	public final static String ZH_MEXICO_CITY = "America/Mexico_City";
	
	
	public final static String TEXT_PLAIN = "txtPlain";
	public final static String TEXT_HTML = "txtHtml";
	public final static String JPEG = "jpeg";
	public final static String PNG = "png";
	public final static String MPEG = "mpeg";
	public final static String OGG = "ogg";
	public final static String MP4 = "mp4";
	public final static String OCTET_STREAM = "octet-stream";
	
	public final static Integer LUNES = 0;
	public final static Integer MARTES = 1;
	public final static Integer MIERCOLES = 2;
	public final static Integer JUEVES = 3;
	public final static Integer VIERNES = 4;
	public final static Integer SABADO= 5;
	public final static Integer DOMINGO = 6;
	
	public final static Integer HR0 = 0;
	public final static Integer HR1 = 1;
	public final static Integer HR2 = 2;
	public final static Integer HR3 = 3;
	public final static Integer HR4 = 4;
	public final static Integer HR5 = 5;
	public final static Integer HR6 = 6;
	public final static Integer HR7 = 7;
	public final static Integer HR8 = 8;
	public final static Integer HR9 = 9;
	public final static Integer HR10 = 10;
	public final static Integer HR11 = 11;
	public final static Integer HR12 = 12;
	public final static Integer HR13 = 13;
	public final static Integer HR14 = 14;
	public final static Integer HR15 = 15;
	public final static Integer HR16 = 16;
	public final static Integer HR17 = 17;
	public final static Integer HR18 = 18;
	public final static Integer HR19 = 19;
	public final static Integer HR20 = 20;
	public final static Integer HR21 = 21;
	public final static Integer HR22 = 22;
	public final static Integer HR23 = 23;

	public final static Map<String, String> CONTENT_TYPE = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put(TEXT_PLAIN, "text/plain");
			put(TEXT_HTML, "text/html");
			put(JPEG, "image/jpeg");
			put(PNG, "image/png");
			put(MPEG, "audio/mpeg");
			put(OGG, "audio/ogg");
			put(MP4, "video/mp4");
			put(OCTET_STREAM, "application/octet-stream");
		}
	});
	
	public final static Map<Integer, String> DIAS = Collections.unmodifiableMap(new HashMap<Integer, String>() {
		{
			put(LUNES, "Lunes");
			put(MARTES, "Martes");
			put(MIERCOLES, "Miércoles");
			put(JUEVES, "Jueves");
			put(VIERNES, "Viernes");
			put(SABADO, "Sabado");
			put(DOMINGO, "Domingo");
		}
	});
	
	public final static Map<Integer, String> HORAS = Collections.unmodifiableMap(new HashMap<Integer, String>() {
		{
			put(HR0, "00:00");
			put(HR1, "01:00");
			put(HR2, "02:00");
			put(HR3, "03:00");
			put(HR4, "04:00");
			put(HR5, "05:00");
			put(HR6, "06:00");
			put(HR7, "07:00");
			put(HR8, "08:00");
			put(HR9, "09:00");
			put(HR10, "10:00");
			put(HR11, "11:00");
			put(HR12, "12:00");
			put(HR13, "13:00");
			put(HR14, "14:00");
			put(HR15, "15:00");
			put(HR16, "16:00");
			put(HR17, "17:00");
			put(HR18, "18:00");
			put(HR19, "19:00");
			put(HR20, "20:00");
			put(HR21, "21:00");
			put(HR22, "22:00");
			put(HR23, "23:00");

		}
	});
	
	
	public final static String EDO_BCAL = "Baja California";
	public final static String EDO_BCAL_S = "Baja California Sur";
	public final static String EDO_CMX = "Ciudad de México";
	public final static String EDO_MEX = "Estado de México";
	public final static String EDO_N_LEON = "Nuevo León";
	public final static String EDO_Q_ROO = "Quintana Roo";
	public final static String EDO_S_LUIS_P = "";
	
	public final static String EDO_BCAL_DB = "Baja California";
	public final static String EDO_BCAL_S_DB = "Baja California Sur";
	public final static String EDO_CMX_DB = "Distrito Federal";
	public final static String EDO_MEX_DB = "México";
	public final static String EDO_N_LEON_DB = "Nuevo León";
	public final static String EDO_Q_ROO_DB = "Quintana Roo";
	public final static String EDO_S_LUIS_P_DB = "San Luis Potosí";
	
	
}
