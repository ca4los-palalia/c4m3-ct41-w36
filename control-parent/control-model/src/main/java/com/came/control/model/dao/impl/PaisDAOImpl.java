package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.PaisDAO;
import com.came.control.model.domain.Pais;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class PaisDAOImpl extends DatabaseMetaclas implements PaisDAO {

	@Transactional
	public void save(Pais entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Pais entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Pais getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Pais.class);

		criteria.add(Restrictions.eq("idPais", idEntity));
		List<Pais> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Pais.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Pais> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Pais.class);

		List<Pais> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Pais.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Pais getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Pais.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Pais> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Pais.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
	
	@Transactional(readOnly = true)
	public Pais getByClave(String clave) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Pais.class);

		criteria.add(Restrictions.eq("clave", clave));
		List<Pais> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Pais.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
