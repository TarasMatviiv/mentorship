package com.mentorship.service.impl;

import com.mentorship.model.Student;
import com.mentorship.persistent.DaoManager;
import com.mentorship.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> findAllStudents() {
        return DaoManager.getStudentDao().findAll();
    }
}
