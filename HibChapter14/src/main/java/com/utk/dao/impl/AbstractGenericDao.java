package com.utk.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.utk.dao.GenericDao;

public class AbstractGenericDao<T> implements GenericDao<T> {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager entityManager;

	private Class<T> clazz;

	public void setClass(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T getById(Long id) {
		return entityManager.createQuery("select e from " + clazz.getName() + " where e.id = :id", clazz)
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public List<T> getAll() {
		return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
	}

	@Override
	public void insert(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void update(Long id, String propertyName, Object propertyValue) {
		entityManager
				.createQuery(
						"update " + clazz.getName() + " e set e." + propertyName + "=:propertyValue where e.id=:id")
				.setParameter("propertyValue", propertyValue).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object propertyValue) {
		return entityManager
				.createQuery("select e from " + clazz.getName() + " e where e." + propertyName + "=:propertyValue",
						clazz)
				.setParameter("propertyValue", propertyValue).getResultList();
	}

}
