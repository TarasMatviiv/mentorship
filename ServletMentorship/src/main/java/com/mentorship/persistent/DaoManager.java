package com.mentorship.persistent;

import com.mentorship.dao.StudentDao;
import com.mentorship.dao.SubjectDao;
import com.mentorship.dao.impl.StudentDaoImpl;
import com.mentorship.dao.impl.SubjectDaoImpl;

public class DaoManager {

    private static SubjectDao subjectDao;
    private static StudentDao studentDao;

    private DaoManager() {
    }

    public static SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = new SubjectDaoImpl();
        }
        return subjectDao;
    }

    public static StudentDao getStudentDao() {
        if (studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }
}
