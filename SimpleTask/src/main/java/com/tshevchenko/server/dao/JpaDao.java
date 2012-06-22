package com.tshevchenko.server.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
/**
 * The JpaDao abstract class to wrap up some data manipulations
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public abstract class JpaDao<Key, Entity> extends JpaDaoSupport {
    protected Class<Entity> entityClass;

    @SuppressWarnings("unchecked")
    public JpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
            .getGenericSuperclass();
        this.entityClass = (Class<Entity>) genericSuperclass
            .getActualTypeArguments()[1];
    }

    public Entity persist(Entity entity) {
        getJpaTemplate().persist(entity);
        return entity;
    }

    public void remove(Entity entity) {
        getJpaTemplate().remove(entity);
    }
	
    public Entity merge(Entity entity) {
        return getJpaTemplate().merge(entity);
    }
	
    public void refresh(Entity entity) {
        getJpaTemplate().refresh(entity);
    }

    public Entity findById(Key id) {
        return getJpaTemplate().find(entityClass, id);
    }

    public Entity flush(Entity entity) {
        getJpaTemplate().flush();
        return entity;
    }
	
    @SuppressWarnings("unchecked")
    public List<Entity> findAll() {
        Object res = getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query q = em.createQuery("SELECT h FROM " + entityClass.getName() + " h");
                return q.getResultList();
            }	
        });
        return (List<Entity>) res;
    }

    @SuppressWarnings("unchecked")
    public Integer removeAll() {
        return (Integer) getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query q = em.createQuery("DELETE FROM " + entityClass.getName() + " h");
                return q.executeUpdate();
            }
        });
    }	
}

