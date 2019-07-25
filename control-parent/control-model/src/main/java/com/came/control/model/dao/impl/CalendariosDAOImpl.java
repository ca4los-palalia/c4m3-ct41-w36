package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.CalendariosDAO;
import com.came.control.model.domain.Calendarios;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class CalendariosDAOImpl extends DatabaseMetaclas implements CalendariosDAO {

	@Transactional
	public void save(Calendarios entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Calendarios entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Calendarios getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Calendarios.class);

		criteria.add(Restrictions.eq("idCalendarios", idEntity));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Calendarios> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Calendarios.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Calendarios> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Calendarios.class);
		criteria.addOrder(Order.asc("organizacion"));
		List<Calendarios> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Calendarios.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;

	}

	@Transactional(readOnly = true)
	public List<Calendarios> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Calendarios.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.asc("organizacion"));
		List<Calendarios> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Calendarios.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
