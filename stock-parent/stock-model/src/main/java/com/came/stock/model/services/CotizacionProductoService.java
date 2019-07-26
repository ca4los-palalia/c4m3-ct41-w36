package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CotizacionProductoDAO;
import com.came.stock.model.domain.CotizacionProducto;

@Service
public class CotizacionProductoService {
	@Autowired
	private CotizacionProductoDAO cotizacionProductoDAO;

	public void save(CotizacionProducto cotizacionProducto) throws DataAccessException {
		cotizacionProductoDAO.save(cotizacionProducto);
	}

	public void delete(CotizacionProducto cotizacionProducto) throws DataAccessException {
		cotizacionProductoDAO.delete(cotizacionProducto);
	}

	public CotizacionProducto getById(Long idCotizacion) throws DataAccessException {
		return cotizacionProductoDAO.getById(idCotizacion);
	}

	public List<CotizacionProducto> getAll() throws DataAccessException {
		return cotizacionProductoDAO.getAll();
	}
}
