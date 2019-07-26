package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.EmailDAO;
import com.came.stock.model.domain.Email;

@Service
public class EmailService {
	@Autowired
	private EmailDAO emailDAO;

	public void save(Email email) throws DataAccessException {
		emailDAO.save(email);
	}

	public void delete(Email email) throws DataAccessException {
		emailDAO.delete(email);
	}

	public Email getById(Long proyecto) throws DataAccessException {
		return emailDAO.getById(proyecto);
	}

	public List<Email> getAll() throws DataAccessException {
		return emailDAO.getAll();
	}

	public Email getUltimoRegistroEmail() throws DataAccessException {
		return emailDAO.getUltimoRegistroEmail();
	}
}
