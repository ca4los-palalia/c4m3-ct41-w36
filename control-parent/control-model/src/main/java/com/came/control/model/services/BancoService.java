package com.came.control.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.control.model.dao.BancoDAO;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Organizacion;

@Service
public class BancoService {

	@Autowired
	private BancoDAO bancoDAO;

	public void save(Banco entity) {
		bancoDAO.save(entity);
	}

	public void delete(Banco entity) {
		bancoDAO.delete(entity);
	}

	public Banco getById(Long idEntity) {
		return bancoDAO.getById(idEntity);
	}

	public Banco getByNombre(String nombreBanco, Organizacion org) {
		return bancoDAO.getByNombre(nombreBanco, org);
	}
	
	public Banco getByClave(String clave, Organizacion org) {
		return bancoDAO.getByClave(clave, org);
	}
	
	public Banco getByRfc(String rfc, Organizacion org) {
		return bancoDAO.getByClave(rfc, org);
	}

	public List<Banco> getAll() {
		return bancoDAO.getAll();
	}

	public List<Banco> getAllByOrganizacion(Organizacion org) {
		return bancoDAO.getAllByOrganizacion(org);
	}
}
