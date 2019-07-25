package com.came.control.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.GrupoSanguineo;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Municipio;
import com.came.control.model.domain.Nacionalidad;
import com.came.control.model.domain.Pais;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Sexo;
import com.came.control.model.domain.ZonaHorario;
import com.came.control.model.services.EstadoCivilService;
import com.came.control.model.services.EstadoService;
import com.came.control.model.services.EstatusService;
import com.came.control.model.services.GrupoSanguineoService;
import com.came.control.model.services.MunicipioService;
import com.came.control.model.services.PaisService;
import com.came.control.model.services.RolService;
import com.came.control.model.services.SexoService;
import com.came.control.model.services.ZonaHorarioService;
import com.came.control.model.util.IteratorList;

public class ImportFromExcelData {

	private XSSFWorkbook workBook;
	
	private PaisService paisService;
	private EstadoService estadoService;
	private MunicipioService municipioService;
	private EstadoCivilService estadoCivilService;
	private GrupoSanguineoService grupoSanguineoService;
	private SexoService sexoService;
	private RolService rolService;
	private ZonaHorarioService zonaHorarioService;
	private EstatusService estatusService;
	private List<Estado> estados;
	private List<Pais> paises;
	
	private IteratorList iteratorList;

	public ImportFromExcelData(XSSFWorkbook workBookIn, PaisService paisServiceIn,
			EstadoService estadoServiceIn, MunicipioService municipioServiceIn, EstadoCivilService estadoCivilServiceIn, GrupoSanguineoService grupoSanguineoServiceIn, SexoService sexoServiceIn, RolService rolServiceIn, EstatusService estatusServiceIn, ZonaHorarioService zonaHorarioServiceIn, IteratorList iteratorLisIn) {
		workBook = workBookIn;
		paisService = paisServiceIn;
		estadoService = estadoServiceIn;
		municipioService = municipioServiceIn;
		estadoCivilService = estadoCivilServiceIn;
		grupoSanguineoService = grupoSanguineoServiceIn;
		sexoService = sexoServiceIn;
		rolService = rolServiceIn;
		estatusService = estatusServiceIn;
		zonaHorarioService = zonaHorarioServiceIn;
		iteratorList = iteratorLisIn;
		estados = new ArrayList<Estado>();
		paises = new ArrayList<Pais>();
	}

	public List<Object>  leerDatosDesdeExcel(int indiceSheet) {
		XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
		Iterator rowIterator = hssfSheet.rowIterator();

		switch (indiceSheet) {
		case 0:// Pais
			extraerPaisesDeExcel(rowIterator);
			break;
		case 1:// Estados
			extraerEstadosDeExcel(rowIterator);
			break;
		case 2:// Municipios
				extraerMunicipiosDeExcel(rowIterator);
			break;
		case 3:// Estados Civil
				extraerEstadosCivilDeExcel(rowIterator);
			break;
		case 4:// Grupo sanguineo
				extraerGrupoSanguineoDeExcel(rowIterator);
			break;
		case 5:// Sexo
				extraerSexoDeExcel(rowIterator);
			break;
		case 6:// Roles
				extraerRolDeExcel(rowIterator);
			break;
		case 7:// Estatus
			extraerEstatusDeExcel(rowIterator);
			break;
		case 8:// Zona horaria
			List<ZonaHorario> listTempZh = extraerZonaHorariaDeExcel(rowIterator);
			return new ArrayList<Object>(listTempZh);
			
		case 9:// Incidencias
			List<Incidencia> listTemp = extraerIncidenciasDeExcel(rowIterator);
			return new ArrayList<Object>(listTemp);
		case 10:// Nacionalidades
			List<Nacionalidad> listTemp2 = extraerNacionalidadesDeExcel(rowIterator);
			return new ArrayList<Object>(listTemp2);
		case 11:// Escolaridad
			List<Escolaridad> listTemp3 = extraerEscolaridadesDeExcel(rowIterator);
			return new ArrayList<Object>(listTemp3);
		case 12:// MetodoPago
			List<MetodoPago> listTemp4 = extraerMetodosPagosDeExcel(rowIterator);
			return new ArrayList<Object>(listTemp4);
		case 13:// Bancos
			List<Banco> listTemp5 = extraerBancosDeExcel(rowIterator);
			return new ArrayList<Object>(listTemp5);
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	private void extraerPaisesDeExcel(Iterator rowIterator) {
		List<Pais> paisesTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Pais nuevoPais = new Pais();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();

			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoPais = crearPais(nuevoPais, hssfCell, j);
					j++;
				}
				nuevoPais.setIcon(nuevoPais.getClave().toLowerCase() + ".png");
				paisesTemp.add(nuevoPais);
			}
			i++;
		}
		if (paisesTemp.size() > 0) {
			for (Pais item : paisesTemp)
				paisService.save(item);
			paises = paisesTemp;
			
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerEstadosDeExcel(Iterator rowIterator) {
		List<Estado> estadosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		paises = paisService.getAll();
		while (rowIterator.hasNext()) {
			Estado nuevoEstado = new Estado();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();

			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 5)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoEstado = crearEstado(nuevoEstado, hssfCell, j);
					j++;
				}
				estadosTemp.add(nuevoEstado);
			}
			i++;
		}
		if (estadosTemp.size() > 0) {
			for (Estado item : estadosTemp)
				estadoService.save(item);
			estados = estadosTemp;
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerMunicipiosDeExcel(Iterator rowIterator) {
		if(estados == null || estados.size() == 0)
			estados = estadoService.getAll();
		
		List<Municipio> municipiosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Municipio nuevoMunicipio = new Municipio();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 3)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoMunicipio = crearMunicipio(nuevoMunicipio, hssfCell, j);
					j++;
				}
				municipiosTemp.add(nuevoMunicipio);
			}
			i++;
		}
		if (municipiosTemp.size() > 0) {
			for (Municipio item : municipiosTemp)
				municipioService.save(item);
			
		}
	}

	
	@SuppressWarnings("rawtypes")
	private void extraerEstadosCivilDeExcel(Iterator rowIterator) {
		List<EstadoCivil> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			EstadoCivil nuevoItem = new EstadoCivil();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearEstadoCivil(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		if (listTemp.size() > 0) {
			for (EstadoCivil item : listTemp)
				estadoCivilService.save(item);		
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerGrupoSanguineoDeExcel(Iterator rowIterator) {
		List<GrupoSanguineo> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			GrupoSanguineo nuevoItem = new GrupoSanguineo();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearGrupoSanguineo(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		if (listTemp.size() > 0) {
			for (GrupoSanguineo item : listTemp)
				grupoSanguineoService.save(item);		
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private void extraerSexoDeExcel(Iterator rowIterator) {
		List<Sexo> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Sexo nuevoItem = new Sexo();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearSexo(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		if (listTemp.size() > 0) {
			for (Sexo item : listTemp)
				sexoService.save(item);		
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerRolDeExcel(Iterator rowIterator) {
		List<Rol> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Rol nuevoItem = new Rol();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearRol(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		if (listTemp.size() > 0) {
			for (Rol item : listTemp)
				rolService.save(item);		
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerEstatusDeExcel(Iterator rowIterator) {
		List<Estatus> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Estatus nuevoItem = new Estatus();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearEstatus(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		if (listTemp.size() > 0) {
			for (Estatus item : listTemp)
				estatusService.save(item);		
		}
	}
	
	@SuppressWarnings("rawtypes")
	private List<ZonaHorario> extraerZonaHorariaDeExcel(Iterator rowIterator) {
		List<ZonaHorario> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ZonaHorario nuevoItem = new ZonaHorario();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 4)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearZonaHorario(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
//		if (listTemp.size() > 0) {
//			for (ZonaHorario item : listTemp)
//				zonaHorarioService.save(item);		
//		}
		return listTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Incidencia> extraerIncidenciasDeExcel(Iterator rowIterator) {
		List<Incidencia> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Incidencia nuevoItem = new Incidencia();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 5)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearIncidencia(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		return listTemp;
	}
	
	
	@SuppressWarnings("rawtypes")
	private List<Nacionalidad> extraerNacionalidadesDeExcel(Iterator rowIterator) {
		List<Nacionalidad> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Nacionalidad nuevoItem = new Nacionalidad();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 4)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearNacionalidad(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		return listTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Escolaridad> extraerEscolaridadesDeExcel(Iterator rowIterator) {
		List<Escolaridad> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Escolaridad nuevoItem = new Escolaridad();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearEscolaridad(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		return listTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<MetodoPago> extraerMetodosPagosDeExcel(Iterator rowIterator) {
		List<MetodoPago> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			MetodoPago nuevoItem = new MetodoPago();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearMetodoPago(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		return listTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Banco> extraerBancosDeExcel(Iterator rowIterator) {
		List<Banco> listTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Banco nuevoItem = new Banco();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 5)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoItem = crearBanco(nuevoItem, hssfCell, j);
					j++;
				}
				listTemp.add(nuevoItem);
			}
			i++;
		}
		return listTemp;
	}
	
	
	
	
	private Pais crearPais(Pais pais, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				pais.setClave(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				pais.setNombre(valor);
			break;
		}
		return pais;
	}

	private Estado crearEstado(Estado estado, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setCapital(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setAbreviatura(valor);
			break;
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setIcon(valor);
			break;
		case 4:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setPais(iteratorList.getPaisByClave(paises, valor));
			break;
		}
		return estado;
	}

	private Municipio crearMunicipio(Municipio municipio, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			municipio.setNombre(valor);

			break;
		case 1:
			municipio.setEstado(iteratorList.getEstadoById(estados, new Long((int) Double.parseDouble(valor))));
			break;
		case 2:
			municipio.setNumeroMunicipio(String.valueOf((int) Double.parseDouble(valor)));
		}
		return municipio;
	}
	
	private EstadoCivil crearEstadoCivil(EstadoCivil newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setNombre(valor);
			break;
		case 1:
			newItem.setDescripcion(valor);
		}
		return newItem;
	}
	
	private GrupoSanguineo crearGrupoSanguineo(GrupoSanguineo newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setNombre(valor);
			break;
		case 1:
			newItem.setDescripcion(valor);
		}
		return newItem;	
	}
	
	private Sexo crearSexo(Sexo newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setNombre(valor);
			break;
		}
		return newItem;	
	}
	
	
	private Rol crearRol(Rol newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setNombre(valor);
			break;
		case 1:
			newItem.setDescripcion(valor);
			break;
		}
		return newItem;	
	}
	
	private Estatus crearEstatus(Estatus newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setNombre(valor);
			break;
		case 1:
			newItem.setDescripcion(valor);
			break;
		}
		return newItem;	
	}
	
	private ZonaHorario crearZonaHorario(ZonaHorario newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			newItem.setUtc(valor);
			break;
		case 1:
			newItem.setZonaHoraria(valor);
			break;
		case 2:
			newItem.setPais(valor);
			break;
		case 3:
			newItem.setPrincipalesPaises(valor);
			break;
		}
		return newItem;	
	}
	
	
	private Incidencia crearIncidencia(Incidencia newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			
			break;
		case 1:
			newItem.setNombre(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 2:
			newItem.setClave(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 3:
			newItem.setDescripcion(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 4:
			
			newItem.setDescuentoPorcentaje(valor != null && !valor.isEmpty() ? Math.round((new Float(valor))) : null);
			break;
		}
		return newItem;	
	}
	
	
	private Nacionalidad crearNacionalidad(Nacionalidad newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			
			break;
		case 1:
			newItem.setCodigoPais(valor != null && !valor.isEmpty() ? String.valueOf((int) Double.parseDouble(valor)) : null);
			break;
		case 2:
			newItem.setNombre(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 3:
			newItem.setClave(valor != null && !valor.isEmpty() ? valor : null);
			break;
		}
		return newItem;	
	}
	
	private Escolaridad crearEscolaridad(Escolaridad newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			break;
		case 1:
			newItem.setNombre(valor != null && !valor.isEmpty() ? valor : null);
			break;
		}
		return newItem;	
	}
	
	private MetodoPago crearMetodoPago(MetodoPago newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			break;
		case 1:
			newItem.setNombre(valor != null && !valor.isEmpty() ? valor : null);
			break;
		}
		return newItem;	
	}
	
	private Banco crearBanco(Banco newItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			break;
		case 1:
			newItem.setClave(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 2:
			newItem.setNombre(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 3:
			newItem.setRfc(valor != null && !valor.isEmpty() ? valor : null);
			break;
		case 4:
			newItem.setDescripcion(valor != null && !valor.isEmpty() ? valor : null);
			break;
		}
		return newItem;	
	}
	
	
	
	
	
}
