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
import com.came.control.model.dao.BancoDAO;
import com.came.control.model.domain.Banco;
import com.came.control.model.domain.Organizacion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class BancoDAOImpl extends DatabaseMetaclas implements BancoDAO {

	@Autowired
	private CtrlUtils ctrlUtils;

	@Transactional
	public void save(Banco entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Banco entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Banco getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.add(Restrictions.eq("idBanco", idEntity));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Banco getByNombre(String nombre, Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Banco> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.addOrder(Order.desc("nombre"));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Banco> getAllByOrganizacion(Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.addOrder(Order.desc("nombre"));
		criteria.add(Restrictions.eq("organizacion", org));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Banco getByClave(String clave, Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.add(Restrictions.eq("clave", clave));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Banco getByRfc(String rfc, Organizacion org) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Banco.class);
		criteria.add(Restrictions.eq("rfc", rfc));
		List<Banco> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Banco.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
