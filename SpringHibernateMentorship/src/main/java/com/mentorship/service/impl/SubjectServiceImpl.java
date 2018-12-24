package com.mentorship.service.impl;

import com.mentorship.dao.SubjectDao;
import com.mentorship.dao.impl.SubjectDaoImpl;
import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;
import com.mentorship.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> findAllSubjects() {
        return subjectDao.findAll();
    }

    @Override
    public Subject findSubjectByTitle(final String title) throws SubjectNotFoundException {
        return subjectDao.findByTitle(title)
                .orElseThrow(SubjectNotFoundException::new);
    }
}
