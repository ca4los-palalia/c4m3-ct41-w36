package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.PeriodoDAO;
import com.came.control.model.domain.Periodo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class PeriodoDAOImpl extends DatabaseMetaclas implements PeriodoDAO {

	@Transactional
	public void save(Periodo entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Periodo entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Periodo getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Periodo.class);

		criteria.add(Restrictions.eq("idCalendarios", idEntity));
		List<Periodo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Periodo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Periodo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Periodo.class);
		List<Periodo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Periodo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Periodo getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Periodo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Periodo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Periodo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
