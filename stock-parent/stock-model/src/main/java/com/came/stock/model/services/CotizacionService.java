package com.came.stock.model.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CotizacionDAO;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;

@Service
public class CotizacionService {
	@Autowired
	private CotizacionDAO cotizacionDAO;

	public void save(Cotizacion cotizacion) throws DataAccessException {
		cotizacionDAO.save(cotizacion);
	}

	public void delete(Cotizacion cotizacion) throws DataAccessException {
		cotizacionDAO.delete(cotizacion);
	}

	public Cotizacion getById(Long idCotizacion) throws DataAccessException {
		return cotizacionDAO.getById(idCotizacion);
	}

	public List<Cotizacion> getAll() throws DataAccessException {
		return cotizacionDAO.getAll();
	}

	public List<Cotizacion> getByFechaEnvioCotizacion(Calendar fechaEnvioSolucion) throws DataAccessException {
		return cotizacionDAO.getByFechaEnvioCotizacion(fechaEnvioSolucion);
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion) throws DataAccessException {
		return cotizacionDAO.getByFechaResolicion(fechaResolucion);
	}

	public List<Cotizacion> getByStatus(Integer status) throws DataAccessException {
		return cotizacionDAO.getByStatus(status);
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return cotizacionDAO.getByProveedor(proveedor);
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return cotizacionDAO.getByRequisicion(requisicion);
	}

	public List<Cotizacion> getTopCompras() {
		return cotizacionDAO.getTopCompras();
	}

	public Long getCountRowsCotizacion() {
		return cotizacionDAO.getCountRowsCotizacion();
	}

	public Cotizacion getCotizacionByFolio(String folioCotizacion) {
		return cotizacionDAO.getCotizacionByFolio(folioCotizacion);
	}

	public List<Cotizacion> getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(String folioCotizacion,
			List<Proveedor> proveedores, List<EstatusRequisicion> estatus) {
		return cotizacionDAO.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(folioCotizacion,
				proveedores, estatus);
	}

	public Cotizacion getCotizacionByRequisicionProveedorAndProducto(Requisicion requisicion, Proveedor proveedor,
			Producto producto) {
		return cotizacionDAO.getCotizacionByRequisicionProveedorAndProducto(requisicion, proveedor, producto);
	}

	public List<Cotizacion> getByProveedorFolioCotizacionNueva(Proveedor proveedor, String folio,
			EstatusRequisicion estatusRequisicion) {
		return cotizacionDAO.getByProveedorFolioCotizacionNueva(proveedor, folio, estatusRequisicion);
	}
}
