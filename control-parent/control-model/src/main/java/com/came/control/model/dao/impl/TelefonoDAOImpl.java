package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.TelefonoDAO;
import com.came.control.model.domain.Telefono;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class TelefonoDAOImpl extends DatabaseMetaclas implements TelefonoDAO {

	@Transactional
	public void save(Telefono entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Telefono entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Telefono getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Telefono.class);
		criteria.add(Restrictions.eq("idTelefono", idEntity));
		List<Telefono> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Telefono.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Telefono> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Telefono.class);
		List<Telefono> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Telefono.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
