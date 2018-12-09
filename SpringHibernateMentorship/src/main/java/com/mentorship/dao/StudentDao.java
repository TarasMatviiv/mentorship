package com.mentorship.dao;

import com.mentorship.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student find(int id);
    void create(Student model);
    void update(Student model);
    void delete(Student model);
}
