package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.PoliticasDAO;
import com.came.control.model.domain.Periodo;
import com.came.control.model.domain.Politicas;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class PoliticasDAOImpl extends DatabaseMetaclas implements PoliticasDAO {

	@Transactional
	public void save(Politicas entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Politicas entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Politicas getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Politicas.class);

		criteria.add(Restrictions.eq("idPoliticas", idEntity));
		List<Politicas> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Politicas.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Politicas> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Politicas.class);
		List<Politicas> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Politicas.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Politicas> getByPeriodo(Periodo periodo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Politicas.class);

		criteria.add(Restrictions.eq("periodo", periodo));
		List<Politicas> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Politicas.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
