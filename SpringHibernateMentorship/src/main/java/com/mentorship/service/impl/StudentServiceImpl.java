package com.mentorship.service.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.dao.SubjectDao;
import com.mentorship.dao.impl.StudentDaoImpl;
import com.mentorship.dao.impl.SubjectDaoImpl;
import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.service.StudentService;
import com.mentorship.util.ValidationUtils;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();
    SubjectDao subjectDao = new SubjectDaoImpl();

    @Override
    public List<Student> findAllStudents() {
        Student student1 = new Student();
        student1.setName("123");
        student1.setAge(12);
        Subject subject = new Subject();
        subject.setTitle("leee");
        subject.setCoefficient(2);
        student1.setSubjects(asList(subject));
        subjectDao.create(subject);
        studentDao.create(student1);
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

        Subject subject = subjectDao.find(Integer.parseInt(subjectIds[0]));
        student.setSubjects(Collections.singletonList(subject));

        studentDao.create(student);
    }
}
