package com.came.control.model.dao.impl;

import java.text.SimpleDateFormat;
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
import com.came.control.model.dao.AsistenciaDAO;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class AsistenciaDAOImpl extends DatabaseMetaclas implements AsistenciaDAO {

	@Autowired
	private CtrlUtils ctrlUtils;
	
	@Transactional
	public void save(Asistencia entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Asistencia entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Asistencia getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("idAsistencia", idEntity));
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Asistencia> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);
		criteria.addOrder(Order.desc("fechaActualizacion"));
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Asistencia> getByOrganizacion(Organizacion organizacion, Date fecha) {
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), false);
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Asistencia.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		
		
		if(fecha != null) {
			criteria.add(Restrictions.ge("fechaActualizacion", stringIni)); 
			criteria.add(Restrictions.lt("fechaActualizacion", stringFin));
		}
		
		criteria.addOrder(Order.desc("fechaActualizacion"));
		
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Asistencia> getByFinalizados(boolean finalizado, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("finalizado", finalizado));
		
		criteria.addOrder(Order.desc("fechaActualizacion"));
		
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Asistencia getByUsrAndDate(Usuario usuario, Date date, Organizacion organizacion) {
		
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(date), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(date), false);
		//date = parseDate(date);

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("usuario", usuario));
//		Restrictions.between("fechaActualizacion", stringIni, stringFin);
		criteria.add(Restrictions.ge("fechaActualizacion", stringIni)); 
		criteria.add(Restrictions.lt("fechaActualizacion", stringFin));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	@Transactional(readOnly = true)
	public List<Asistencia> getByUsrAndDateWeek(Usuario usuario, Date fechaInicio, Date fechaFin,
			Organizacion organizacion) {
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fechaInicio), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fechaFin), false);
		//date = parseDate(date);

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.ge("fechaActualizacion", stringIni)); 
		criteria.add(Restrictions.lt("fechaActualizacion", stringFin));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Asistencia> getByFecha(Date fecha, Organizacion organizacion) {
		fecha = parseDate(fecha);

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Asistencia.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("fechaActualizacion", fecha));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
	
	@Transactional(readOnly = true)
	public List<Asistencia> getAllWhithFecha(Date fecha) {
		String stringIni = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), true);
		String stringFin = getFechaControladaString(ctrlUtils.convertirDateToCalendar(fecha), false);
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Asistencia.class);
		
		criteria.add(Restrictions.ge("fechaActualizacion", stringIni)); 
		criteria.add(Restrictions.lt("fechaActualizacion", stringFin));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		
		List<Asistencia> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Asistencia.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	private Date parseDate(Date dateIput) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = format.format(dateIput);
			return format.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}
}
