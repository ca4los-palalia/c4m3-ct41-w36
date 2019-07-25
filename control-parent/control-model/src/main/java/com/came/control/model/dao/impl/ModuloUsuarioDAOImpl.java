package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.ModuloUsuarioDAO;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Usuario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class ModuloUsuarioDAOImpl extends DatabaseMetaclas implements ModuloUsuarioDAO{
	

	@Transactional
	public void save(ModuloUsuario moduloUsuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(moduloUsuario);
	}

	@Transactional
	public void delete(ModuloUsuario moduloUsuario) {
		sessionFactory.getCurrentSession().delete(moduloUsuario);
	}

	@Transactional(readOnly = true)
	public ModuloUsuario getById(Long idModuloUsuario) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ModuloUsuario.class);

		criteria.add(Restrictions.eq("idModuloUsuario", idModuloUsuario));
		List<ModuloUsuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ModuloUsuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ModuloUsuario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ModuloUsuario.class);
		List<ModuloUsuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ModuloUsuario.class));

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ModuloUsuario> getByUsuario(Usuario usuario) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ModuloUsuario.class, "mu");

		criteria.createAlias("mu.modulo", "mod");
		
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.addOrder(Order.asc("mod.nombre"));
		List<ModuloUsuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), ModuloUsuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
