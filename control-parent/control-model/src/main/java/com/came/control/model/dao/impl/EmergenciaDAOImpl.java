package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.EmergenciaDAO;
import com.came.control.model.domain.Emergencia;
import com.came.control.model.domain.Persona;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class EmergenciaDAOImpl extends DatabaseMetaclas implements EmergenciaDAO {

	@Transactional
	public void save(Emergencia entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Emergencia entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Emergencia getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Emergencia.class);

		criteria.add(Restrictions.eq("idEmergencia	", idEntity));
		List<Emergencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Emergencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Emergencia> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Emergencia.class);
		List<Emergencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Emergencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Emergencia getByPersona(Persona persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Emergencia.class);

		criteria.add(Restrictions.eq("persona	", persona));
		List<Emergencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Emergencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
