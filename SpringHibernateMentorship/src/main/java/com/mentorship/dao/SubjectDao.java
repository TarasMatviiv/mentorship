package com.mentorship.dao;

import com.mentorship.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {
    List<Subject> findAll();
    Optional<Subject> findByTitle(String title);
    Subject find(int id);
    void create(Subject model);
    void update(Subject model);
    void delete(Subject model);
}
