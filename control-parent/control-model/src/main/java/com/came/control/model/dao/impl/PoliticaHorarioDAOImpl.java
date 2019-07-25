package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.PoliticaHorarioDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.PoliticaHorario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class PoliticaHorarioDAOImpl extends DatabaseMetaclas implements PoliticaHorarioDAO {

	@Transactional
	public void save(PoliticaHorario entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(PoliticaHorario entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public PoliticaHorario getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(PoliticaHorario.class);

		criteria.add(Restrictions.eq("idPoliticaHorario", idEntity));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<PoliticaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), PoliticaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<PoliticaHorario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(PoliticaHorario.class);

		List<PoliticaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), PoliticaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public PoliticaHorario getByNombre(String nombre, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(PoliticaHorario.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<PoliticaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), PoliticaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<PoliticaHorario> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(PoliticaHorario.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<PoliticaHorario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), PoliticaHorario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
