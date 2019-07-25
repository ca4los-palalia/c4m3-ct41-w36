package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.SexoDAO;
import com.came.control.model.domain.Sexo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class SexoDAOImpl extends DatabaseMetaclas implements SexoDAO {

	@Transactional
	public void save(Sexo entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Sexo entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Sexo getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Sexo.class);

		criteria.add(Restrictions.eq("idSexo", idEntity));
		List<Sexo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Sexo.class));
		;
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Sexo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Sexo.class);
		List<Sexo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Sexo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Sexo getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Sexo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Sexo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Sexo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
