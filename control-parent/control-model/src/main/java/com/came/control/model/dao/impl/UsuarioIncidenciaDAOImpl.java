package com.came.control.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.UsuarioIncidenciaDAO;
import com.came.control.model.domain.Incidencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.UsuarioIncidencia;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class UsuarioIncidenciaDAOImpl extends DatabaseMetaclas implements UsuarioIncidenciaDAO {

	@Autowired
	private CtrlUtils ctrlUtils;
	
	@Transactional
	public void save(UsuarioIncidencia entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(UsuarioIncidencia entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	@Transactional(readOnly = true)
	public UsuarioIncidencia getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("idUsuarioIncidencia", idEntity));
		
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	@Transactional(readOnly = true)
	public List<UsuarioIncidencia> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);
		criteria.addOrder(Order.desc("fecha"));
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	@Transactional(readOnly = true)
	public List<UsuarioIncidencia> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.desc("fecha"));
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	@Transactional(readOnly = true)
	public List<UsuarioIncidencia> getByUsuario(Usuario usuario, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.addOrder(Order.desc("fecha"));
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	@Transactional(readOnly = true)
	public List<UsuarioIncidencia> getByUsuarioAndFecha(Usuario usuario, Organizacion organizacion, Date fecha) {
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), false);
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.ge("fecha", stringIni)); 
		criteria.add(Restrictions.lt("fecha", stringFin));
		criteria.addOrder(Order.desc("fecha"));
		
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	@Transactional(readOnly = true)
	public List<UsuarioIncidencia> getByIncidenciaAndFecha(Incidencia incidencia, Organizacion organizacion,
			Date fecha) {
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), false);
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(UsuarioIncidencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("incidencia", incidencia));
		criteria.add(Restrictions.ge("fecha", stringIni)); 
		criteria.add(Restrictions.lt("fecha", stringFin));
		criteria.addOrder(Order.desc("fecha"));
		
		List<UsuarioIncidencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), UsuarioIncidencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
