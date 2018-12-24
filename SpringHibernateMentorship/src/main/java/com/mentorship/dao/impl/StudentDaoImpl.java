package com.mentorship.dao.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao {

    public StudentDaoImpl() {
        super(Student.class);
    }
}
