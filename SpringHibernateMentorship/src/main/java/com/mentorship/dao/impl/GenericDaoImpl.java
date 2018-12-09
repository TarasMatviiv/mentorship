package com.mentorship.dao.impl;

import com.mentorship.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<T extends Serializable> {

    private Class<T> clazz;
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(int id) {
        Session currentSession = getCurrentSession();
        Transaction tx = currentSession.beginTransaction();
        T t = currentSession.get(clazz, id);
        tx.commit();
        return t;
    }

    public List findAll() {
        Session currentSession = getCurrentSession();
        Transaction tx = currentSession.beginTransaction();
        List list = currentSession.createQuery("from " + clazz.getName()).list();
        tx.commit();
        return list;
    }

    public void create(T model) {
        Session currentSession = getCurrentSession();
        Transaction tx = currentSession.beginTransaction();
        currentSession.persist(model);
        tx.commit();
    }

    public void update(T model) {
        getCurrentSession().merge(model);
    }

    public void delete(T model) {
        getCurrentSession().delete(model);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
