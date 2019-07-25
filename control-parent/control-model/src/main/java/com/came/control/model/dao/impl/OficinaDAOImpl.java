package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.OficinaDAO;
import com.came.control.model.domain.Direccion;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class OficinaDAOImpl extends DatabaseMetaclas implements OficinaDAO {

	@Transactional
	public void save(Oficina entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Oficina entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Oficina getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Oficina.class);

		criteria.add(Restrictions.eq("idOficina", idEntity));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Oficina> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Oficina.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Oficina> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Oficina.class);
		List<Oficina> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Oficina.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Oficina> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Oficina.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Oficina> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Oficina.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Oficina getByDireccion(Direccion direccion, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Oficina.class);

		criteria.add(Restrictions.eq("direccion", direccion));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Oficina> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Oficina.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
