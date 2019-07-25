package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.EscolaridadDAO;
import com.came.control.model.domain.Escolaridad;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class EscolaridadDAOImpl extends DatabaseMetaclas implements EscolaridadDAO {

	@Autowired
	private CtrlUtils ctrlUtils;

	@Transactional
	public void save(Escolaridad entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Escolaridad entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Escolaridad getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Escolaridad.class);
		criteria.add(Restrictions.eq("idEscolaridad", idEntity));
		List<Escolaridad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Escolaridad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Escolaridad getByNombre(String nombreEscolaridad, Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Escolaridad.class);
		criteria.add(Restrictions.eq("nombre", nombreEscolaridad));
		criteria.add(Restrictions.eq("organizacion", org));
		List<Escolaridad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Escolaridad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	@Transactional(readOnly = true)
	public List<Escolaridad> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Escolaridad.class);
		criteria.addOrder(Order.desc("nombre"));
		List<Escolaridad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Escolaridad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	
	
	@Transactional(readOnly = true)
	public List<Escolaridad> getAllByOrganizacion(Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Escolaridad.class);
		criteria.add(Restrictions.eq("organizacion", org));
		criteria.addOrder(Order.desc("nombre"));
		List<Escolaridad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Escolaridad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	

}
