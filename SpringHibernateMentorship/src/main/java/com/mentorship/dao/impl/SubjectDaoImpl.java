package com.mentorship.dao.impl;

import com.mentorship.dao.SubjectDao;
import com.mentorship.model.Subject;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SubjectDaoImpl extends GenericDaoImpl<Subject> implements SubjectDao {

    private static final String GET_BY_TITLE = "select s from Subject s where s.title = :title";

    public SubjectDaoImpl() {
        super(Subject.class);
    }

    public Optional<Subject> findByTitle(final String title) {
        return getSession().createQuery(GET_BY_TITLE, Subject.class)
                .setParameter("title", title)
                .setMaxResults(1)
                .uniqueResultOptional();
    }
}
