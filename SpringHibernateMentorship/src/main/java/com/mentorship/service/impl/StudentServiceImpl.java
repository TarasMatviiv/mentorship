package com.mentorship.service.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.dao.impl.StudentDaoImpl;
import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.service.StudentService;
import com.mentorship.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student findStudent(int id) {
        return studentDao.find(id);
    }

    @Override
    public void createStudent(final String name, final String age, final List<Integer> subjectIds) throws MandatoryValuesMissingException {
        ValidationUtils.validateNotEmpty(name, "Name should not be null");
        ValidationUtils.validateNotEmpty(age, "Age should not be null");

        Student student = new Student();
        student.setName(name);
        student.setAge(Integer.parseInt(age));
        student.setSubjects(createSubjectsFromIds(subjectIds));

        studentDao.create(student);
    }

    private List<Subject> createSubjectsFromIds(final List<Integer> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }
}
