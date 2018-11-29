package com.mentorship.service;

import com.mentorship.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();
    void createStudent(String name, String age, String[] subjectIdsAsString) throws Exception;
}
