package com.mentorship.dao.impl;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable>  {
    List<T> findAll();
    T find(long id);
    void create(T model);
    void update(T model);
    void delete(T model);
}
