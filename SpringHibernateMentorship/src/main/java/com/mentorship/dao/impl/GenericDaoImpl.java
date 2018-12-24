package com.mentorship.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(int id) {
        return getSession().get(clazz, id);
    }

    public List findAll() {
        return getSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(T model) {
        getSession().save(model);
    }

    public void update(T model) {
        getSession().merge(model);
    }

    public void delete(T model) {
        getSession().delete(model);
    }

    protected final Session getSession() {
        return sessionFactory.openSession();
    }
}
