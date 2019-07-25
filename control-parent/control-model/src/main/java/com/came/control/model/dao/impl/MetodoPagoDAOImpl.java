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
import com.came.control.model.dao.MetodoPagoDAO;
import com.came.control.model.domain.MetodoPago;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class MetodoPagoDAOImpl extends DatabaseMetaclas implements MetodoPagoDAO {

	@Autowired
	private CtrlUtils ctrlUtils;

	@Transactional
	public void save(MetodoPago entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(MetodoPago entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public MetodoPago getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(MetodoPago.class);
		criteria.add(Restrictions.eq("idMetodoPago", idEntity));
		List<MetodoPago> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), MetodoPago.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public MetodoPago getByNombre(String nombre, Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(MetodoPago.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<MetodoPago> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), MetodoPago.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<MetodoPago> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(MetodoPago.class);
		criteria.addOrder(Order.desc("nombre"));
		List<MetodoPago> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), MetodoPago.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<MetodoPago> getAllByOrganizacion(Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(MetodoPago.class);
		criteria.addOrder(Order.desc("nombre"));
		criteria.add(Restrictions.eq("organizacion", org));
		List<MetodoPago> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), MetodoPago.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
