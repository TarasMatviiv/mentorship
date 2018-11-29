package com.mentorship.service.impl;

import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.persistent.DaoManager;
import com.mentorship.service.StudentService;
import com.mentorship.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> findAllStudents() {
        return DaoManager.getStudentDao().findAll();
    }

    @Override
    public void createStudent(final String name, final String age, final String[] subjectIds) throws MandatoryValuesMissingException {
        ValidationUtils.validateNotEmpty(name, "Name should not be null");
        ValidationUtils.validateNotEmpty(age, "Age should not be null");

        Student student = new Student();
        student.setName(name);
        student.setAge(Integer.parseInt(age));

        List<Subject> rawSubjects = createRawSubjectsFromIds(subjectIds);
        student.setSubjects(rawSubjects);

        DaoManager.getStudentDao().create(student);
    }

    private List<Subject> createRawSubjectsFromIds(String[] subjectIds) {
        List<Subject> subjects = new ArrayList<>();
        if (subjectIds != null) {
            for (String idAsString : subjectIds) {
                Subject subject = new Subject();
                subject.setId(Integer.parseInt(idAsString));
                subjects.add(subject);
            }
        }
        return subjects;
    }
}
