package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.IncidenciaDAO;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class IncidenciaDAOImpl extends DatabaseMetaclas implements IncidenciaDAO {

	@Transactional
	public void save(Incidencia entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Incidencia entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Incidencia getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Incidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("idIncidencia", idEntity));

		List<Incidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Incidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Incidencia> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Incidencia.class);
		List<Incidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Incidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Incidencia> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Incidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Incidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Incidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Incidencia getByNombre(String nombre, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Incidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("nombre", nombre));

		List<Incidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Incidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Incidencia getByClave(String clave, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Incidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("clave", clave));

		List<Incidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Incidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
