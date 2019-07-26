package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.EstatusRequisicionDAO;
import com.came.stock.model.domain.EstatusRequisicion;

@Service
public class EstatusRequisicionService {
	@Autowired
	private EstatusRequisicionDAO estatusRequisicionDAO;

	public void save(EstatusRequisicion estatusRequisicion) {
		estatusRequisicionDAO.save(estatusRequisicion);
	}

	public void delete(EstatusRequisicion estatusRequisicion) {
		estatusRequisicionDAO.delete(estatusRequisicion);
	}

	public EstatusRequisicion getById(Long idEstatusRequisicion) {
		return estatusRequisicionDAO.getById(idEstatusRequisicion);
	}

	public List<EstatusRequisicion> getAll() {
		return estatusRequisicionDAO.getAll();
	}

	public EstatusRequisicion getByNombre(String nombre) {
		return estatusRequisicionDAO.getByNombre(nombre);
	}

	public EstatusRequisicion getByClave(String clave) {
		return estatusRequisicionDAO.getByClave(clave);
	}

	public EstatusRequisicion getByEstado(boolean estado) {
		return estatusRequisicionDAO.getByEstado(estado);
	}
}
