package com.mentorship.dao;

import com.mentorship.model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> findAll();
    List<Student> findAllRawBySubjectId(int subjectId);
    Student findById(int studentId);
    Student findByName(String studentName);
    void create(Student student);
}
