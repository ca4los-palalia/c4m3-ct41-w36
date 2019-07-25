package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.GeolocalizacionDAO;
import com.came.control.model.domain.Geolocalizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class GeolocalizacionDAOImpl extends DatabaseMetaclas implements GeolocalizacionDAO {

	@Transactional
	public void save(Geolocalizacion entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Geolocalizacion entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Geolocalizacion getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Geolocalizacion.class);

		criteria.add(Restrictions.eq("idGeolocalizacion", idEntity));
		List<Geolocalizacion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Geolocalizacion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Geolocalizacion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Geolocalizacion.class);
		List<Geolocalizacion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Geolocalizacion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;

	}

}
