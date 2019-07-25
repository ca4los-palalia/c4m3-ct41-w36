package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.MetodoPagoDAO;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Organizacion;

@Service
public class MetodoPagoService {

	@Autowired
	private MetodoPagoDAO metodoPagoDAO;

	public void save(MetodoPago entity) {
		metodoPagoDAO.save(entity);
	}

	public void delete(MetodoPago entity) {
		metodoPagoDAO.delete(entity);
	}

	public MetodoPago getById(Long idEntity) {
		return metodoPagoDAO.getById(idEntity);
	}

	public MetodoPago getByNombre(String nombreMetodoPago, Organizacion org) {
		return metodoPagoDAO.getByNombre(nombreMetodoPago, org);
	}

	public List<MetodoPago> getAll() {
		return metodoPagoDAO.getAll();
	}

	public List<MetodoPago> getAllByOrganizacion(Organizacion org) {
		return metodoPagoDAO.getAllByOrganizacion(org);
	}
}
