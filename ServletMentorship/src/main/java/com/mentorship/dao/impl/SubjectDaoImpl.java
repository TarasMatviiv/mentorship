package com.mentorship.dao.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.dao.SubjectDao;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.persistent.ConnectionManager;
import com.mentorship.persistent.DaoManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

    private static final String FIND_ALL = "SELECT * FROM " + Subject.TABLE_NAME;
    private static final String FIND_BY_TITLE = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE title = ?;";
    private static final String FIND_ALL_SUBJECT_IDS_BY_STUDENT_ID = "SELECT " + Subject.ID + " FROM student_subject WHERE " + Student.ID + " = ?";

    @Override
    public List<Subject> findAll() {
        Connection connection = ConnectionManager.getConnection();

        List<Subject> subjects = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet rs = s.executeQuery(FIND_ALL)) {

            while (rs.next()) {
                Subject subject = new Subject();
                int subjectId = rs.getInt(Subject.ID);
                subject.setId(subjectId);
                subject.setTitle(rs.getString(Subject.TITLE));
                subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                subject.setStudents(DaoManager.getStudentDao().findAllRawBySubjectId(subjectId));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public Subject findByTitle(final String title) {
        Connection connection = ConnectionManager.getConnection();

        Subject subject = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    int subjectId = rs.getInt(Subject.ID);
                    subject.setId(subjectId);
                    subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                    subject.setTitle(title);
                    subject.setStudents(DaoManager.getStudentDao().findAllRawBySubjectId(subjectId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<Subject> findAllRawByStudentId(final int studentId) {
        Connection connection = ConnectionManager.getConnection();
        List<Subject> rawSubjects = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_SUBJECT_IDS_BY_STUDENT_ID)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getInt(Subject.ID));
                    rawSubjects.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rawSubjects;
    }
}