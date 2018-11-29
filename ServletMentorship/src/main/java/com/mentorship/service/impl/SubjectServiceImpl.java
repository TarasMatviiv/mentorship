package com.mentorship.service.impl;

import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;
import com.mentorship.persistent.DaoManager;
import com.mentorship.service.SubjectService;

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
        }

        return subject;
    }

    @Override
    public List<Integer> findAllSubjectIds() {
        return DaoManager.getSubjectDao().findAllIds();
    }
}
