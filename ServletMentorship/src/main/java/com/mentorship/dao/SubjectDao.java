package com.mentorship.dao;

import com.mentorship.model.Subject;

import java.util.List;

public interface SubjectDao {

    List<Subject> findAll();
    Subject findByTitle(String title);
    List<Subject> findAllRawByStudentId(int studentId);
    List<Integer> findAllIds();
}
