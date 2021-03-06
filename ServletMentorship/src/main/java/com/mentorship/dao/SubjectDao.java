package com.mentorship.dao;

import com.mentorship.model.Subject;

import java.util.List;

public interface SubjectDao {

    List<Subject> findAll();
    Subject findByTitle(String title);

    Subject findById(int id);

    List<Subject> findAllByStudentId(int studentId);
    List<Integer> findAllIds();
}
