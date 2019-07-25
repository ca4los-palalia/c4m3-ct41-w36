package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.OrganizacionDAO;
import com.came.control.model.domain.Geolocalizacion;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class OrganizacionDAOImpl extends DatabaseMetaclas implements OrganizacionDAO {

	@Transactional
	public void save(Organizacion entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Organizacion entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Organizacion getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Organizacion.class);

		criteria.add(Restrictions.eq("idOrganizacion", idEntity));
		List<Organizacion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Organizacion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Organizacion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Organizacion.class);

		List<Organizacion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Organizacion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Organizacion getByRfc(String rfc) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Organizacion.class);

		criteria.add(Restrictions.eq("rfc", rfc));
		List<Organizacion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Organizacion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Organizacion getByGeolocalizacion(Geolocalizacion geolocalizacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
