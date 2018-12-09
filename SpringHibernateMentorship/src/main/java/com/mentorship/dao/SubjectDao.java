package com.mentorship.dao;

import com.mentorship.model.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();
    Subject find(int id);
    void create(Subject model);
    void update(Subject model);
    void delete(Subject model);
}
