package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.MunicipioDAO;
import com.came.control.model.domain.Estado;
import com.came.control.model.domain.Municipio;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class MunicipioDAOImpl extends DatabaseMetaclas implements MunicipioDAO {

	@Transactional
	public void save(Municipio entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Municipio entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Municipio getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("idMunicipio", idEntity));
		List<Municipio> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Municipio.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Municipio> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Municipio.class);
		criteria.addOrder(Order.asc("nombre"));
		List<Municipio> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Municipio.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Municipio getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Municipio> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Municipio.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Municipio> getByEstado(Estado estado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Municipio.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("estado", estado));
		List<Municipio> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Municipio.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
