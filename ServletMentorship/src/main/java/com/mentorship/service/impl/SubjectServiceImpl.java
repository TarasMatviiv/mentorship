package com.mentorship.service.impl;

import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.persistent.DaoManager;
import com.mentorship.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    @Override
    public List<Subject> findAllSubjects() {
        return DaoManager.getSubjectDao().findAll();
    }

    @Override
    public Subject findSubjectByTitle(final String title) throws SubjectNotFoundException {
        Subject subject = DaoManager.getSubjectDao().findByTitle(title);

        if (subject == null) {
            throw new SubjectNotFoundException();
        } else {
            List<Student> students = new ArrayList<>();
            subject.getStudents()                              //make full student from raw (only with ids)
                    .stream()
                    .map(Student::getId)
                    .forEach(id -> students.add(DaoManager.getStudentDao().findById(id)));
            subject.setStudents(students);
        }

        return subject;
    }
}
