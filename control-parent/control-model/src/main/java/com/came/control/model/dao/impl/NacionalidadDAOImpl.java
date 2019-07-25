package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.NacionalidadDAO;
import com.came.control.model.domain.Nacionalidad;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class NacionalidadDAOImpl extends DatabaseMetaclas implements NacionalidadDAO {

	@Autowired
	private CtrlUtils ctrlUtils;

	@Transactional
	public void save(Nacionalidad entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Nacionalidad entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Nacionalidad getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Nacionalidad.class);
		criteria.add(Restrictions.eq("idNacionalidad", idEntity));
		List<Nacionalidad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Nacionalidad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Nacionalidad> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Nacionalidad.class);
		criteria.addOrder(Order.desc("nombre"));
		List<Nacionalidad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Nacionalidad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Nacionalidad getByIdCodigoPais(String codigoPais) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Nacionalidad.class);
		criteria.add(Restrictions.eq("codigoPais", codigoPais));
		List<Nacionalidad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Nacionalidad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	@Transactional(readOnly = true)
	public Nacionalidad getByNombre(String nombreNacionalidad) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Nacionalidad.class);
		criteria.add(Restrictions.eq("nombre", nombreNacionalidad));
		List<Nacionalidad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Nacionalidad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	@Transactional(readOnly = true)
	public Nacionalidad getByClave(String claveNacinalidad) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Nacionalidad.class);
		criteria.add(Restrictions.eq("clave", claveNacinalidad));
		List<Nacionalidad> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Nacionalidad.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	

}
