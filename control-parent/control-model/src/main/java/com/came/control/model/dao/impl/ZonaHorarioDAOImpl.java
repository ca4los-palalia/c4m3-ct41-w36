package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.ZonaHorarioDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.ZonaHorario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class ZonaHorarioDAOImpl extends DatabaseMetaclas implements ZonaHorarioDAO {

	@Transactional
	public void save(ZonaHorario entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(ZonaHorario entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public ZonaHorario getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ZonaHorario.class);

		criteria.add(Restrictions.eq("idZonaHorario", idEntity));
		List<ZonaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ZonaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	@Transactional(readOnly = true)
	public ZonaHorario getByZonaHorario(String zonaHoraria, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ZonaHorario.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("zonaHoraria", zonaHoraria));
		criteria.addOrder(Order.asc("utc"));
		List<ZonaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ZonaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ZonaHorario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ZonaHorario.class);
		criteria.addOrder(Order.asc("utc"));
		List<ZonaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ZonaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	
	@Transactional(readOnly = true)
	public List<ZonaHorario> getByOrganizacion(Organizacion org){
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ZonaHorario.class);
		criteria.add(Restrictions.eq("organizacion", org));
		criteria.addOrder(Order.asc("zonaHoraria"));
		List<ZonaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ZonaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
