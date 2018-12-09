package com.mentorship.dao.impl;

import com.mentorship.dao.SubjectDao;
import com.mentorship.model.Subject;

public class SubjectDaoImpl extends GenericDaoImpl<Subject> implements SubjectDao {

    public SubjectDaoImpl() {
        super(Subject.class);
    }
}
