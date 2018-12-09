package com.mentorship.service.impl;

import com.mentorship.dao.SubjectDao;
import com.mentorship.dao.impl.SubjectDaoImpl;
import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;
import com.mentorship.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    SubjectDao subjectDao = new SubjectDaoImpl();

    @Override
    public List<Subject> findAllSubjects() {
        return subjectDao.findAll();
    }

    @Override
    public Subject findSubjectByTitle(final String title) throws SubjectNotFoundException {
        Subject subject = subjectDao.find(1);

        if (subject == null) {
            throw new SubjectNotFoundException();
        }

        return subject;
    }
}
