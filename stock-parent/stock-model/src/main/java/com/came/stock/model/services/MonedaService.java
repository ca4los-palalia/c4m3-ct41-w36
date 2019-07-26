package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.MonedaDAO;
import com.came.stock.model.domain.Moneda;

@Service
public class MonedaService {
	@Autowired
	private MonedaDAO monedaDAO;

	public void save(Moneda moneda) throws DataAccessException {
		monedaDAO.save(moneda);
	}

	public void delete(Moneda moneda) throws DataAccessException {
		monedaDAO.delete(moneda);
	}

	public Moneda getById(Long idMoneda) throws DataAccessException {
		return monedaDAO.getById(idMoneda);
	}

	public List<Moneda> getAll() throws DataAccessException {
		return monedaDAO.getAll();
	}
}
