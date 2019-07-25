package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.DatosGeneralesDAO;
import com.came.control.model.domain.DatosGenerales;
import com.came.control.model.domain.EstadoCivil;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Persona;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class DatosGeneralesDAOImpl extends DatabaseMetaclas implements DatosGeneralesDAO {

	@Transactional
	public void save(DatosGenerales entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(DatosGenerales entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public DatosGenerales getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("idDatosGenerales", idEntity));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<DatosGenerales> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<DatosGenerales> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public DatosGenerales getByPersona(Persona persona, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("persona", persona));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public DatosGenerales getByRfc(String rfc, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("rfc", rfc));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public DatosGenerales getByNss(String nss, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("nss", nss));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public DatosGenerales getByCurp(String curp, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("curp", curp));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public DatosGenerales getByEstadoCivil(EstadoCivil estadoCivil, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(DatosGenerales.class);

		criteria.add(Restrictions.eq("estadoCivil", estadoCivil));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<DatosGenerales> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), DatosGenerales.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
