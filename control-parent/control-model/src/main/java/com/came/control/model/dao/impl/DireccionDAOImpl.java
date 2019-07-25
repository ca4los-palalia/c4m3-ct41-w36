package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.DireccionDAO;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Estado;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class DireccionDAOImpl extends DatabaseMetaclas implements DireccionDAO {

	@Transactional
	public void save(Direccion entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Direccion entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Direccion getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("idCalendarios", idEntity));
		List<Direccion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Direccion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Direccion.class);
		List<Direccion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Direccion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByCp(String cp) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("cp", cp));
		List<Direccion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Direccion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByEstado(Estado estado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("estado", estado));
		List<Direccion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Direccion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
