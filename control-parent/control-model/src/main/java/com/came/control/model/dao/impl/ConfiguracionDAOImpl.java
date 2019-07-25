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
import com.came.control.model.dao.ConfiguracionDAO;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class ConfiguracionDAOImpl extends DatabaseMetaclas implements ConfiguracionDAO {

	@Autowired
	private CtrlUtils ctrlUtils;

	@Transactional
	public void save(Configuracion entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Configuracion entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Configuracion getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Configuracion.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("idConfiguracion", idEntity));
		List<Configuracion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Configuracion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Configuracion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Configuracion.class);
		criteria.addOrder(Order.desc("fechaActualizacion"));
		List<Configuracion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Configuracion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Configuracion> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Configuracion.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		List<Configuracion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Configuracion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Configuracion getByClaveAndOrg(String clave, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Configuracion.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("clave", clave));
		List<Configuracion> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Configuracion.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
