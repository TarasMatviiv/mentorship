package com.mentorship.service;

import com.mentorship.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();
    Student findStudent(int id);
    void createStudent(String name, String age, List<Integer> subjectIds) throws Exception;
}
