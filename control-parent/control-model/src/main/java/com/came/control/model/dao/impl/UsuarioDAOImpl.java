package com.came.control.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.control.model.DatabaseMetaclas;
import com.came.control.model.dao.UsuarioDAO;
import com.came.control.model.domain.Estatus;
import com.came.control.model.domain.Oficina;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.domain.Usuario;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Repository
public class UsuarioDAOImpl extends DatabaseMetaclas implements UsuarioDAO {

	@Transactional
	public void save(Usuario entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void delete(Usuario entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Transactional(readOnly = true)
	public Usuario getById(Long idEntity, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("idUsuario", idEntity));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getByEstatus(Estatus estatus, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("estatus", estatus));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getByOficina(Oficina oficina, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("oficina", oficina));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Usuario getAutenticacWithNip(String nip, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("nip", nip));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Usuario getAutenticacWithWeb(String usuario, String contrasenia, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("nombreUsuario", usuario));
		criteria.add(Restrictions.eq("contrasenia", contrasenia));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		;
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Usuario> getByRol(Rol rol, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(Usuario.class);

		criteria.add(Restrictions.eq("rol", rol));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Usuario> lista = ImmutableList.copyOf(Iterables.filter(criteria.list(), Usuario.class));
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
