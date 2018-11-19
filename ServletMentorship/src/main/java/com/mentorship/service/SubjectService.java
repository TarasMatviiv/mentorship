package com.mentorship.service;

import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAllSubjects();
    Subject findSubjectByTitle(String title) throws SubjectNotFoundException;
}
