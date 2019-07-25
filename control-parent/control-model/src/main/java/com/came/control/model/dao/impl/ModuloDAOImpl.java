package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.ModuloDAO;
import com.came.control.model.domain.Modulo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class ModuloDAOImpl extends DatabaseMetaclas implements ModuloDAO{
	
	

	@Transactional
	public void save(Modulo modulo) {
		sessionFactory.getCurrentSession().saveOrUpdate(modulo);
		
	}

	@Transactional
	public void delete(Modulo modulo) {
		sessionFactory.getCurrentSession().delete(modulo);
	}

	@Transactional(readOnly = true)
	public Modulo getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Modulo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Modulo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Modulo.class));
		return (lista != null && !lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Modulo getByRuta(String ruta) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Modulo.class);

		criteria.add(Restrictions.eq("ruta", ruta));
		List<Modulo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Modulo.class));
		return (lista != null && !lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Modulo getById(Long idModulo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Modulo.class);

		criteria.add(Restrictions.eq("idModulo", idModulo));
		List<Modulo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Modulo.class));
		return (lista != null && !lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Modulo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Modulo.class);
		List<Modulo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Modulo.class));;
		return (lista != null && !lista.isEmpty()) ? lista : null;
	}
}
