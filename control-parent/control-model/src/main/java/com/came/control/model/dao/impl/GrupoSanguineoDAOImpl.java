package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.GrupoSanguineoDAO;
import com.came.control.model.domain.GrupoSanguineo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class GrupoSanguineoDAOImpl extends DatabaseMetaclas implements GrupoSanguineoDAO {

	@Transactional
	public void save(GrupoSanguineo entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(GrupoSanguineo entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public GrupoSanguineo getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(GrupoSanguineo.class);

		criteria.add(Restrictions.eq("idGrupoSanguineo", idEntity));
		List<GrupoSanguineo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), GrupoSanguineo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<GrupoSanguineo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(GrupoSanguineo.class);
		List<GrupoSanguineo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), GrupoSanguineo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public GrupoSanguineo getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(GrupoSanguineo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<GrupoSanguineo> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), GrupoSanguineo.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

}
