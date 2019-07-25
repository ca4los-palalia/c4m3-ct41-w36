package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.RolDAO;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class RolDAOImpl extends DatabaseMetaclas implements RolDAO {

	@Transactional
	public void save(Rol entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Rol entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Rol getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Rol.class);

		criteria.add(Restrictions.eq("idRol", idEntity));
		List<Rol> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Rol.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Rol> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Rol.class);
		List<Rol> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Rol.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Rol getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Rol.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Rol> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Rol.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Rol> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Rol.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Rol> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Rol.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
