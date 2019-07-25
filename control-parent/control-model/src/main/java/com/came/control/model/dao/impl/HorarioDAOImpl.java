package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.HorarioDAO;
import com.came.control.model.domain.Horario;
import com.came.control.model.domain.Usuario;
import com.came.control.model.domain.ZonaHorario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class HorarioDAOImpl extends DatabaseMetaclas implements HorarioDAO {

	@Transactional
	public void save(Horario entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Horario entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Horario getById(Long idEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Horario.class);

		criteria.add(Restrictions.eq("idHorario", idEntity));
		List<Horario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Horario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Horario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Horario.class);
		List<Horario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Horario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Horario> getByUsuario(Usuario usuario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Horario.class);

		criteria.add(Restrictions.eq("usuario", usuario));
		List<Horario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Horario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Horario> getWithDescanso(boolean descanso) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Horario.class);

		criteria.add(Restrictions.eq("descanso", descanso));
		List<Horario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Horario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Horario> getByZonaHorario(ZonaHorario zonaHorario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Horario.class);

		criteria.add(Restrictions.eq("zonaHorario", zonaHorario));
		List<Horario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Horario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
