package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.EstadoDAO;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Pais;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class EstadoDAOImpl extends DatabaseMetaclas implements EstadoDAO {

	@Transactional
	public void save(Estado entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Estado entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Estado getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);

		criteria.add(Restrictions.eq("idEstado", idEntity));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Estado> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);
		criteria.addOrder(Order.asc("nombre"));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Estado getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	public Estado getByAbreviatura(String abreviatura) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);

		criteria.add(Restrictions.eq("abreviatura", abreviatura));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	public Estado getByCapital(String capital) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);

		criteria.add(Restrictions.eq("capital", capital));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Estado> getByPais(Pais pais) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Estado.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("pais", pais));
		List<Estado> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Estado.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
