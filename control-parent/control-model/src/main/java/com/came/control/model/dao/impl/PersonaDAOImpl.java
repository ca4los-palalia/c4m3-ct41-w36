package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.PersonaDAO;
import com.came.control.model.domain.Persona;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class PersonaDAOImpl extends DatabaseMetaclas implements PersonaDAO {

	@Transactional
	public void save(Persona entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Persona entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Persona getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Persona.class);

		criteria.add(Restrictions.eq("idPersona", idEntity));
		List<Persona> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Persona.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Persona> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Persona.class);

		List<Persona> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Persona.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
