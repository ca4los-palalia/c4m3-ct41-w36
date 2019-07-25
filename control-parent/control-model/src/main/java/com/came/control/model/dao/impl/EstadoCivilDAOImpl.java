package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.EstadoCivilDAO;
import com.came.control.model.domain.EstadoCivil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class EstadoCivilDAOImpl extends DatabaseMetaclas implements EstadoCivilDAO {

	@Transactional
	public void save(EstadoCivil entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(EstadoCivil entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public EstadoCivil getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstadoCivil.class);

		criteria.add(Restrictions.eq("idEstadoCivil", idEntity));
		List<EstadoCivil> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), EstadoCivil.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<EstadoCivil> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstadoCivil.class);
		List<EstadoCivil> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), EstadoCivil.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	
	}

	@Transactional(readOnly = true)
	public EstadoCivil getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstadoCivil.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<EstadoCivil> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), EstadoCivil.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
