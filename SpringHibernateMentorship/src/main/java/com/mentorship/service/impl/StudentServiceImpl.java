package com.mentorship.service.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.dao.impl.StudentDaoImpl;
import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.service.StudentService;
import com.mentorship.util.ValidationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student findStudent(int id) {
        return studentDao.find(id);
    }

    @Override
    public void createStudent(final String name, final String age, final String[] subjectIds) throws MandatoryValuesMissingException {
        ValidationUtils.validateNotEmpty(name, "Name should not be null");
        ValidationUtils.validateNotEmpty(age, "Age should not be null");

        Student student = new Student();
        student.setName(name);
        student.setAge(Integer.parseInt(age));

        Subject subject = new Subject();
        subject.setTitle("MATH");
        subject.setCoefficient(3);

        student.setSubjects(Collections.singletonList(subject));

        studentDao.create(student);
    }

}
