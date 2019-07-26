package com.cplsystems.stock.app.vm.proveedor;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Telefono;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public abstract class ProveedorMetaClass extends ProveedorVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {

		nuevoProveedor = new Proveedor();
		municipioProveedor = new Municipio();
		estadoProveedor = new Estado();
		paisProveedor = new Pais();
		telefonoProveedor = new Telefono();
		telefonoContacto = new Telefono();
		emailProveedor = new Email();
		emailContacto = new Email();
		contactoProveedor = new Contacto();
		contactoContacto = new Contacto();
		direccionProveedor = new Direccion();
		nuevoProveedor = new Proveedor();
		representanteLegal = new Persona();
		personaContacto = new Persona();
		monedaSeleccionada = new Moneda();
		bancoSeleccionado = new Banco();
		contrato = new Contrato();
		cuentaPago = new CuentaPago();
		buscarProveedor = new Proveedor();
		buscarProducto = new Producto();
		proveedorProducto = new ProveedorProducto();
		buscarProveedorAsociar = new Proveedor();
		/*
		botonMuenu1 = new MenuButtonsActivated();
		botonMuenu2 = new MenuButtonsActivated();
		botonMuenu3 = new MenuButtonsActivated();
		botonMuenu4 = new MenuButtonsActivated();
		botonMuenu5 = new MenuButtonsActivated();
		botonMuenu6 = new MenuButtonsActivated();
		botonMuenu7 = new MenuButtonsActivated();
		botonMuenu8 = new MenuButtonsActivated();
		*/
		proveedorSelected = new Proveedor();
		proveedorSelected.setDireccionFiscal(new Direccion());
		Contacto contact = new Contacto();
		Email correo = new Email();
		contact.setTelefono(new Telefono());
		contact.setEmail(correo);
		proveedorSelected.setContacto(contact);

		Persona p = new Persona();

		Contacto contactPersona = new Contacto();
		Email correoPersona = new Email();
		Telefono telefonoPersona = new Telefono();
		contactPersona.setTelefono(telefonoPersona);
		contactPersona.setEmail(correoPersona);

		p.setContacto(contactPersona);
		proveedorSelected.setRepresentanteAteCliente(p);
		
		proveedoresAsociacionSelected = new Proveedor();
	}

	public void initProperties() {
		contratos = contratoService.getAll();
		estados = estadoService.getAll();
		paises = paisService.getAll();
		municipios = municipioService.getAll();
		bancosDB = bancoService.getAll();
		monedasDB = monedaService.getAll();
		paisProveedor = paisService.findById(Long.valueOf(157L));
		giros = giroService.getAll();
		proveedoresLista = proveedorService.getAll();
	}

	
	public String validarEntradaDatosProveedor() {
		String mensajes = "";
		if ((nuevoProveedor.getNombre().equals(null)) || (nuevoProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre del proveedor";
		} else if ((paisProveedor.getNombre() == null) || (paisProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un pa�s para la direcci�n del proveedor";
		} else if ((estadoProveedor.getNombre() == null) || (estadoProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un estado para la direcci�n del proveedor";
		} else if ((municipioProveedor.getNombre() == null) || (municipioProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un municipio para la direcci�n del proveedor";
		} else if ((nuevoProveedor.getDireccionFiscal().getColonia() == null)
				|| (nuevoProveedor.getDireccionFiscal().getColonia().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre de una colonia para la direcci�n del proveedor";
		} else if ((nuevoProveedor.getDireccionFiscal().getCalle() == null)
				|| (nuevoProveedor.getDireccionFiscal().getCalle().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre de una calle para la direcci�n del proveedor";
		} else if ((nuevoProveedor.getDireccionFiscal().getNumExt() == null)
				|| (nuevoProveedor.getDireccionFiscal().getNumExt().isEmpty())) {
			mensajes = "Es necesario ingresar un numero externo domiciliario para la direcci�n del proveedor";
		} else if ((nuevoProveedor.getContacto().getEmail().getEmail() == null)
				|| (nuevoProveedor.getContacto().getEmail().getEmail().isEmpty())) {
			mensajes = "Es necesario ingresar un correo electronico para contactar al proveedor";
		} else if ((nuevoProveedor.getContacto().getTelefono().getNumero() == null)
				|| (nuevoProveedor.getContacto().getTelefono().getNumero().isEmpty())) {
			mensajes = "Es necesario ingresar un n�mero telefonico para contactar al proveedor";
		} else if ((nuevoProveedor.getRepresentanteAteCliente().getApellidoPaterno() == null)
				|| (nuevoProveedor.getRepresentanteAteCliente().getApellidoPaterno().isEmpty())) {
			mensajes = "Es necesario ingresar apellido paterno del contacto";
		} else if ((nuevoProveedor.getRepresentanteAteCliente().getApellidoMaterno() == null)
				|| (nuevoProveedor.getRepresentanteAteCliente().getApellidoMaterno().isEmpty())) {
			mensajes = "Es necesario ingresar apellido materno del contacto";
		} else if ((nuevoProveedor.getRepresentanteAteCliente().getNombre() == null)
				|| (nuevoProveedor.getRepresentanteAteCliente().getNombre().isEmpty())) {
			mensajes = "Es necesario ingresar nombre de pila del contacto";
		} else if ((nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono().getNumero() == null)
				|| (nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono().getNumero().isEmpty())) {
			mensajes = "Es necesario ingresar un n�mero telefonico del contacto";
		} else if ((nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail().getEmail() == null)
				|| (nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail().getEmail().isEmpty())) {
			mensajes = "Es necesario ingresar un correo electr�nico del contacto";
		} else if (monedaSeleccionada.equals(null)) {
			mensajes = "Es necesario seleccionar un tipo de moneda para la cuenta pago";
		} else if (bancoSeleccionado.equals(null)) {
			mensajes = "Es necesario seleccionar un banco para la cuenta pago";
		} else if (cuentaPago.getCuentaBancaria().equals(null)) {
			mensajes = "Es necesario ingresar un n�mero de cuenta bancaria";
		}
		return mensajes;
	}

	@Command
	@NotifyChange({ "*" })
	public void nuevoProveedor() {
		municipioProveedor = new Municipio();
		paisProveedor = new Pais();
		estadoProveedor = new Estado();
		emailContacto = new Email();
		emailProveedor = new Email();
		estadoProveedor = new Estado();
		monedaSeleccionada = new Moneda();
		municipioProveedor = new Municipio();
		nuevoProveedor = new Proveedor();
		paisProveedor = new Pais();
		personaContacto = new Persona();
		telefonoContacto = new Telefono();
		telefonoProveedor = new Telefono();
	}

	public void guardarProveedor() {
		nuevoProveedor.getContacto().getEmail().getEmail();
		if (!guardadoEmailProveedor) {
			emailService.save(nuevoProveedor.getContacto().getEmail());
			guardadoEmailProveedor = true;
		}
		if (!guardadoEmailContacto) {
			emailService.save(nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail());
			guardadoEmailContacto = true;
		}
		if (!guardadoTelefonoProveedor) {
			telefonoService.save(nuevoProveedor.getContacto().getTelefono());
			guardadoTelefonoProveedor = true;
		}
		if (!guardadoTelefonoContacto) {
			telefonoService.save(nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono());
			guardadoTelefonoContacto = true;
		}
		if (!guardadoContactoProveedor) {
			contactoProveedor.setTelefono(nuevoProveedor.getContacto().getTelefono());
			contactoProveedor.setEmail(nuevoProveedor.getContacto().getEmail());
			contactoService.save(contactoProveedor);
			guardadoContactoProveedor = true;
		}
		if (!guardadoContactoContacto) {
			contactoContacto.setEmail(nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail());
			contactoContacto.setTelefono(nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono());
			contactoService.save(contactoContacto);
			guardadoContactoContacto = true;
		}
		if (!guardadoDireccionProveedor) {
			direccionProveedor.setMunicipio(municipioProveedor);
			direccionProveedor.setEstado(estadoProveedor);
			direccionProveedor.setPais(paisProveedor);
			direccionProveedor.setNumExt(nuevoProveedor.getDireccionFiscal().getNumExt().toUpperCase());
			if (nuevoProveedor.getDireccionFiscal().getNumInt() != null) {
				direccionProveedor.setNumInt(nuevoProveedor.getDireccionFiscal().getNumInt().toUpperCase());
			}
			direccionService.save(direccionProveedor);
			guardadoDireccionProveedor = true;
		}
		if (!guardadorepResentanteLegalProveedor) {
			representanteLegal.setNombre(nuevoProveedor.getNombre());
			representanteLegal.setContacto(contactoProveedor);
			representanteLegal.setDireccion(direccionProveedor);
			personaService.save(representanteLegal);
			guardadorepResentanteLegalProveedor = true;
		}
		if (!guardadoPersonaContacto) {
			personaContacto.setCurp(proveedorSelected.getRepresentanteAteCliente().getCurp().toUpperCase());
			personaContacto.setRfc(proveedorSelected.getRepresentanteAteCliente().getRfc().toUpperCase());
			personaContacto.setContacto(contactoContacto);
			personaService.save(personaContacto);
			guardadoPersonaContacto = true;
		}
		if (!guardadoNuevoProveedor) {
			nuevoProveedor.setRfc(nuevoProveedor.getRfc().toUpperCase());
			nuevoProveedor.setRepresentanteAteCliente(personaContacto);
			nuevoProveedor.setContacto(contactoContacto);
			nuevoProveedor.setRepresentanteLegal(representanteLegal);
			nuevoProveedor.setDireccionFiscal(direccionProveedor);
			nuevoProveedor.setClave(nuevoProveedor.getNombre().substring(0, 2).toUpperCase()
					+ nuevoProveedor.getRfc().substring(nuevoProveedor.getRfc().length() - 2).toUpperCase()
					+ estadoProveedor.getNombre().substring(0, 1).toUpperCase());

			nuevoProveedor.setFechaActualizacion(Calendar.getInstance());
			proveedorService.save(nuevoProveedor);
			guardadoNuevoProveedor = true;
		}
		if (!guardadoCuentaPago) {
			cuentaPago.setProveedor(nuevoProveedor);
			cuentaPago.setBanco(bancoSeleccionado);
			cuentaPago.setMoneda(monedaSeleccionada);
			cuentasPagoService.save(cuentaPago);
			guardadoCuentaPago = true;
		}
		if ((contrato != null) && (contrato.getFechaVigenciaInicio() != null)
				&& (contrato.getFechaVigenciaFin() != null)) {
			contratoService.save(contrato);
			nuevoProveedor.setContrato(contrato);
		}
	}

	public void actualizarProveedorCambios() {
		if (proveedorSelected != null) {
			Organizacion org = (Organizacion) sessionUtils.getFromSession("FIRMA");
			proveedorSelected.setOrganizacion(org);
			proveedorService.save(proveedorSelected);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void asignarProductosAProveedor() {
		Integer actualizados = Integer.valueOf(0);
		Integer noActualizados = Integer.valueOf(0);
		String mensaje = "";
		String mensajeNoActualizado = "";
		if (proveedoresAsociacionSelected != null) {
			if (productosDB != null) {
				for (Producto productoSeleccionado : productosDB) {
					if (productoSeleccionado.isSeleccionar()) {
						List<ProveedorProducto> proveedorProducto = proveedorProductoService
								.getByProductoProveedor(productoSeleccionado, proveedoresAsociacionSelected);

						boolean guardarProducto = true;
						if ((proveedorProducto != null) && (proveedorProducto.size() > 0)) {
							guardarProducto = false;
						}
						Integer localInteger1;
						if (guardarProducto) {
							nuevoProveedorProducto = new ProveedorProducto();
							nuevoProveedorProducto.setProveedor(proveedoresAsociacionSelected);

							nuevoProveedorProducto.setProducto(productoSeleccionado);

							proveedorProductoService.save(nuevoProveedorProducto);

							localInteger1 = actualizados;
							Integer localInteger2 = actualizados = Integer.valueOf(actualizados.intValue() + 1);
						} else {
							// nuevoProveedorProducto = noActualizados;
							localInteger1 = noActualizados = Integer.valueOf(noActualizados.intValue() + 1);
						}
					}
				}
				ProveedorProducto nuevoProveedorProducto;
				for (Producto producto : productosDB) {
					if (producto.isSeleccionar()) {
						producto.setSeleccionar(false);
					}
				}
				proveedorProductos = proveedorProductoService.getByProveedor(proveedoresAsociacionSelected);
				if (noActualizados.intValue() > 0) {
					mensajeNoActualizado = "Se detectaron productos existentes para este proveedor [" + noActualizados
							+ "].";
				}
				mensaje = getMensajeAsignacionProductoAProveedor(actualizados, "asignado") + ". " + mensajeNoActualizado
						+ "";

				StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La lista de productos se encuentra vacia", "error", Integer.valueOf(0),
						null);
			}
		} else {
			StockUtils.showSuccessmessage("No ha sido seleccionado un proveedor", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void quitarProductosDeProveedor() {
		Integer registrosRemovidos = Integer.valueOf(0);
		if (proveedoresAsociacionSelected != null) {
			if ((proveedorProductos != null) && (proveedorProductos.size() > 0)) {
				List<ProveedorProducto> removerProductos = new ArrayList();
				for (ProveedorProducto proveedorProducto : proveedorProductos) {
					Integer localInteger1;
					if (proveedorProducto.getProducto().isSeleccionar()) {
						removerProductos.add(proveedorProducto);

						proveedorProductoService.delete(proveedorProducto);
						localInteger1 = registrosRemovidos;
						Integer localInteger2 = registrosRemovidos = Integer.valueOf(registrosRemovidos.intValue() + 1);
					}
					proveedorProducto.getProducto().setSeleccionar(false);
				}
				proveedorProductos = proveedorProductoService.getByProveedor(proveedoresAsociacionSelected);

				String mensaje = "";
				if (registrosRemovidos.intValue() > 0) {
					mensaje = "Se removieron " + registrosRemovidos + " productos del proveedor -"
							+ proveedoresAsociacionSelected.getNombre() + "-";
				} else {
					mensaje = "Ningun producto fue removido para el proveedor -"
							+ proveedoresAsociacionSelected.getNombre() + "-";
				}
				StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("-" + proveedoresAsociacionSelected.getNombre()
						+ "- no cuenta con productos. Nada que eliminar", "warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("No ha sido seleccionado un proveedor", "warning", Integer.valueOf(0), null);
		}
	}

	private String getMensajeAsignacionProductoAProveedor(Integer contador, String nombreAccionSingular) {
		String mensaje = "";
		if (contador.intValue() > 1) {
			mensaje = contador + " productos han sido " + nombreAccionSingular + "s al proveedor -"
					+ proveedoresAsociacionSelected.getNombre() + "-";
		} else if (contador.intValue() == 0) {
			mensaje = "Ning�n producto fue " + nombreAccionSingular + " al proveedor -"
					+ proveedoresAsociacionSelected.getNombre() + "-";
		} else if (contador.intValue() == 1) {
			mensaje = contador + " producto ha sido " + nombreAccionSingular + " al proveedor -"
					+ proveedoresAsociacionSelected.getNombre() + "-";
		}
		return mensaje;
	}

	@Command
	@NotifyChange({ "proveedorSelected", "paisProveedor", "estadoProveedor", "municipioProveedor", "cuentaPago",
			"monedaSeleccionada", "bancoSeleccionado", "contratoVigenciaInicio", "proveedorProductos", "monedasDB" })
	public void obtenerInformacionProveedorBuscado() {
		monedaSeleccionada = new Moneda();
		bancoSeleccionado = new Banco();
		cuentaPago = new CuentaPago();
		
		if (proveedorSelected != null) {

			if (proveedorSelected.getGiro() != null)
				proveedorSelected.setGiro(getGiroByIdFromList(proveedorSelected.getGiro().getIdGiro(), giros));
			if (proveedorSelected.getDireccionFiscal() != null) {
				Direccion dir = proveedorSelected.getDireccionFiscal();
				paisProveedor = getPaisFromList(dir.getPais().getIdPais());
				estadoProveedor = getEstadoFromList(dir.getEstado().getIdEstado());
				municipioProveedor = getMunicipioFromList(dir.getMunicipio().getIdMunicipio());
			}

			List<CuentaPago> cp = cuentasPagoService.getByProveedor(proveedorSelected);
			if (cp != null) {
				cuentaPago = ((CuentaPago) cp.get(0));
				monedaSeleccionada = getMonedaFromList(cuentaPago.getMoneda().getIdMoneda());
				bancoSeleccionado = getBancoByIdFromList(cuentaPago.getBanco().getIdBanco(), bancosDB);
			}
 			if (proveedorSelected.getContrato() != null) {
				contratoVigenciaInicio = new StockUtils()
						.convertirCalendarToDate(proveedorSelected.getContrato().getFechaVigenciaInicio());

				contratoVigenciaFin = new StockUtils()
						.convertirCalendarToDate(proveedorSelected.getContrato().getFechaVigenciaFin());
			}
			if (proveedorProductos == null) {
				proveedorProductos = new ArrayList();
			}
			proveedorProductos = proveedorProductoService.getByProveedor(proveedorSelected);
		}
	}

	@Command
	@NotifyChange({ "proveedorProductos" })
	public void seleccionarProveedorRelacionProducto() {
		if (proveedoresAsociacionSelected != null) {
			proveedorProductos = proveedorProductoService.getByProveedor(proveedoresAsociacionSelected);
		}
	}

	/*
	 * 
	 * public void leerDatosDesdeExcel(InputStream inPutStream, int indiceSheet)
	 * { boolean escribirDatos = true; List<Proveedor> productoNuevosExcel = new
	 * ArrayList(); try { XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
	 * XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet); Iterator
	 * rowIterator = hssfSheet.rowIterator(); Integer i = 0; try { int j; while
	 * (rowIterator.hasNext()) { Proveedor proveedorNuevo = new Proveedor();
	 * XSSFRow hssfRow = (XSSFRow) rowIterator.next(); Iterator iterator =
	 * hssfRow.cellIterator(); if (i > 0) { j = 0; XSSFCell hssfCell; while
	 * ((iterator.hasNext()) && (j < 30)) { hssfCell = (XSSFCell)
	 * iterator.next(); proveedorNuevo = crearProveedor(proveedorNuevo,
	 * hssfCell, j); j++; } proveedorNuevo.setOrganizacion((Organizacion)
	 * sessionUtils.getFromSession("FIRMA"));
	 * 
	 * productoNuevosExcel.add(proveedorNuevo); } j = i;
	 * 
	 * } } catch (Exception e) { escribirDatos = false; File borrarArchivo = new
	 * File(fileName); borrarArchivo.delete(); } File borrarArchivo = new
	 * File(fileName); Organizacion org = null; if ((borrarArchivo.delete()) &&
	 * (escribirDatos)) { org = (Organizacion)
	 * sessionUtils.getFromSession("FIRMA"); for (Proveedor item :
	 * productoNuevosExcel) { Direccion direccionSave =
	 * item.getDireccionFiscal(); direccionService.save(direccionSave);
	 * 
	 * Contacto contactoSave = item.getContacto(); Telefono telefonoSave =
	 * contactoSave.getTelefono(); Email emailSave = contactoSave.getEmail();
	 * emailService.save(emailSave); telefonoService.save(telefonoSave);
	 * contactoService.save(contactoSave);
	 * 
	 * Persona representanteLegal = item.getRepresentanteLegal(); contactoSave =
	 * representanteLegal.getContacto(); telefonoSave =
	 * contactoSave.getTelefono(); emailSave = contactoSave.getEmail();
	 * emailService.save(emailSave); telefonoService.save(telefonoSave);
	 * contactoService.save(contactoSave);
	 * personaService.save(representanteLegal);
	 * 
	 * Contrato contratoSave = item.getContrato();
	 * contratoSave.setOrganizacion((Organizacion)
	 * sessionUtils.getFromSession("FIRMA"));
	 * 
	 * contratoSave
	 * .setFechaActualizacion(stockUtils.convertirCalendarToString(Calendar.
	 * getInstance()));
	 * 
	 * contratoService.save(contratoSave);
	 * 
	 * item.setProveedorActivo(true);
	 * item.setFechaActualizacion(Calendar.getInstance());
	 * item.setClave(item.getNombre().substring(0, 2).toUpperCase() +
	 * item.getRfc().substring(item.getRfc().length() - 2).toUpperCase() +
	 * item.getDireccionFiscal().getEstado().getNombre().substring(0,
	 * 1).toUpperCase());
	 * 
	 * item.setOrganizacion(org); proveedorService.save(item);
	 * 
	 * CuentaPago cuentaPagoSave = item.getCuentaPago();
	 * cuentaPagoSave.setProveedor(item); } } } catch (Exception e) {
	 * Organizacion org; escribirDatos = false; e.printStackTrace(); } }
	 * 
	 */
	private Proveedor crearProveedor(Proveedor nuevoProveedor, XSSFCell valorDePropiedad, int indice) {
		String valor = "";
		switch (indice) {
		case 0:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setNombre(valor);
			}
			break;
		case 1:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Giro giroEntrada = giroService.getById(Long.valueOf(valor));
				nuevoProveedor.setGiro(giroEntrada);
			}
			break;
		case 2:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setRazonSocial(valor);
			}
			break;
		case 3:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setRfc(valor);
			}
			break;
		case 4:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Pais paisEntrada = paisService.findById(Long.valueOf(valor));
				direccionFiscal.setPais(paisEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 5:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Estado estadoEntrada = estadoService.getById(Long.valueOf(valor));
				direccionFiscal.setEstado(estadoEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 6:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCuidad(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 7:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Municipio municipioEntrada = municipioService.getById(Long.valueOf(valor));

				direccionFiscal.setMunicipio(municipioEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 8:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setColonia(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 9:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCalle(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 10:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setNumExt(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 11:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setNumInt(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 12:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCp(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 13:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Telefono telefonoEntrada;
				if (contactoEntrada != null) {
					telefonoEntrada = contactoEntrada.getTelefono();
					if (telefonoEntrada == null) {
						telefonoEntrada = new Telefono();
					}
				} else {
					contactoEntrada = new Contacto();
					telefonoEntrada = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoEntrada.setNumero(valor);
				contactoEntrada.setTelefono(telefonoEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 14:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Telefono telefonoEntrada;
				if (contactoEntrada != null) {
					telefonoEntrada = contactoEntrada.getTelefono();
					if (telefonoEntrada == null) {
						telefonoEntrada = new Telefono();
					}
				} else {
					contactoEntrada = new Contacto();
					telefonoEntrada = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoEntrada.setFax(valor);
				contactoEntrada.setTelefono(telefonoEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 15:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Email emailEntrada;
				if (contactoEntrada != null) {
					emailEntrada = contactoEntrada.getEmail();
					if (emailEntrada == null) {
						emailEntrada = new Email();
					}
				} else {
					contactoEntrada = new Contacto();
					emailEntrada = new Email();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				emailEntrada.setEmail(valor);
				contactoEntrada.setEmail(emailEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 16:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setPaginaWeb(String.valueOf(valorDePropiedad));
			}
			break;
		case 17:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setApellidoPaterno(String.valueOf(valorDePropiedad));

				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 18:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setApellidoMaterno(String.valueOf(valorDePropiedad));

				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 19:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setNombre(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 20:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setRfc(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 21:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setCurp(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 22:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Telefono telefonoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						telefonoContacto = contactoContacto.getTelefono();
						if (telefonoContacto == null) {
							telefonoContacto = new Telefono();
						}
					} else {
						contactoContacto = new Contacto();
						telefonoContacto = new Telefono();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					telefonoContacto = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoContacto.setNumero(valor);
				contactoContacto.setTelefono(telefonoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 23:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Email correoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						correoContacto = contactoContacto.getEmail();
						if (correoContacto == null) {
							correoContacto = new Email();
						}
					} else {
						contactoContacto = new Contacto();
						correoContacto = new Email();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					correoContacto = new Email();
				}
				correoContacto.setEmail(String.valueOf(valorDePropiedad));
				contactoContacto.setEmail(correoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 24:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Telefono telefonoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						telefonoContacto = contactoContacto.getTelefono();
						if (telefonoContacto == null) {
							telefonoContacto = new Telefono();
						}
					} else {
						contactoContacto = new Contacto();
						telefonoContacto = new Telefono();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					telefonoContacto = new Telefono();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoContacto.setCelular(valor);
				contactoContacto.setTelefono(telefonoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 25:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contrato contratoEntrada = nuevoProveedor.getContrato();
				if (contratoEntrada == null) {
					contratoEntrada = new Contrato();
				}
				valor = String.valueOf(valorDePropiedad);
				String dia = "";
				String mes = "";
				String anyo = "";

				int counter = 0;
				for (int i = 0; i < valor.length(); i++) {
					String caracter = valor.substring(i, i + 1);
					boolean concatenar = true;
					if (caracter.equals(".")) {
						concatenar = false;
						counter++;
					}
					switch (counter) {
					case 0:
						if (concatenar) {
							dia = dia + caracter;
						}
						break;
					case 1:
						if (concatenar) {
							mes = mes + caracter;
						}
						break;
					case 2:
						if (concatenar) {
							anyo = anyo + caracter;
						}
						break;
					}
				}
				Integer diaInteger = Integer.valueOf(Integer.parseInt(dia));
				Integer mesInteger = Integer.valueOf(Integer.parseInt(mes));
				Integer anyoInteger = Integer.valueOf(Integer.parseInt(anyo));
				contratoEntrada.setFechaVigenciaInicio(
						stockUtils.convertirStringToCalendar(diaInteger, mesInteger, anyoInteger));

				nuevoProveedor.setContrato(contratoEntrada);
			}
			break;
		case 26:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contrato contratoEntrada = nuevoProveedor.getContrato();
				if (contratoEntrada == null) {
					contratoEntrada = new Contrato();
				}
				valor = String.valueOf(valorDePropiedad);
				String dia = "";
				String mes = "";
				String anyo = "";

				int counter = 0;
				for (int i = 0; i < valor.length(); i++) {
					String caracter = valor.substring(i, i + 1);
					boolean concatenar = true;
					if (caracter.equals(".")) {
						concatenar = false;
						counter++;
					}
					switch (counter) {
					case 0:
						if (concatenar) {
							dia = dia + caracter;
						}
						break;
					case 1:
						if (concatenar) {
							mes = mes + caracter;
						}
						break;
					case 2:
						if (concatenar) {
							anyo = anyo + caracter;
						}
						break;
					}
				}
				Integer diaInteger = Integer.valueOf(Integer.parseInt(dia));
				Integer mesInteger = Integer.valueOf(Integer.parseInt(mes));
				Integer anyoInteger = Integer.valueOf(Integer.parseInt(anyo));
				contratoEntrada
						.setFechaVigenciaFin(stockUtils.convertirStringToCalendar(diaInteger, mesInteger, anyoInteger));

				nuevoProveedor.setContrato(contratoEntrada);
			}
			break;
		case 27:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Moneda monedaEntrada = monedaService.getById(Long.valueOf(valor));

				cuentaPagoEntrada.setMoneda(monedaEntrada);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 28:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Banco bancoEntrada = bancoService.getById(Long.valueOf(valor));
				cuentaPagoEntrada.setBanco(bancoEntrada);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 29:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				cuentaPagoEntrada.setCuentaBancaria(valor);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 30:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setComentario(valor);
			}
			break;
		}
		return nuevoProveedor;
	}
}
