package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.EstatusDAO;
import com.came.control.model.domain.Estatus;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class EstatusDAOImpl extends DatabaseMetaclas implements EstatusDAO {

	@Transactional
	public void save(Estatus entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Estatus entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Estatus getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estatus.class);

		criteria.add(Restrictions.eq("idEstatus", idEntity));
		List<Estatus> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estatus.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Estatus> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estatus.class);
		List<Estatus> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estatus.class));
		return (lista != null) && (!lista.isEmpty()) ? lista: null;
	}

	@Transactional(readOnly = true)
	public Estatus getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estatus.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Estatus> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estatus.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
