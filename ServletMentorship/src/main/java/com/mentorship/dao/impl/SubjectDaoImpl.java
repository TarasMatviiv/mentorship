package com.mentorship.dao.impl;

import com.mentorship.dao.SubjectDao;
import com.mentorship.model.Subject;
import com.mentorship.persistent.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

    private static final Logger LOG = Logger.getLogger(SubjectDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM " + Subject.TABLE_NAME;
    private static final String FIND_BY_TITLE = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE " + Subject.TITLE + " = ?;";
    private static final String FIND_BY_ID = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE " + Subject.ID + " = ?;";
    private static final String FIND_ALL_BY_STUDENT_ID = "SELECT subject.* FROM " + Subject.TABLE_NAME
            + " JOIN student_subject ON subject.subject_id = student_subject.subject_id "
            + " WHERE student_subject.student_id  = ?";
    private static final String FIND_ALL_IDS = "SELECT " + Subject.ID + " FROM " + Subject.TABLE_NAME;

    @Override
    public List<Subject> findAll() {
        Connection connection = ConnectionManager.getConnection();

        List<Subject> subjects = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet rs = s.executeQuery(FIND_ALL)) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt(Subject.ID));
                subject.setTitle(rs.getString(Subject.TITLE));
                subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            LOG.error("Can not get all subjects", e);
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
                    subject.setId(rs.getInt(Subject.ID));
                    subject.setTitle(title);
                    subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not get subject by title", e);
        }
        return subject;
    }

    @Override
    public Subject findById(final int id) {
        Connection connection = ConnectionManager.getConnection();
        Subject subject = null;

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setId(id);
                    subject.setTitle(rs.getString(Subject.TITLE));
                    subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not get subject by id", e);
        }
        return subject;
    }

    @Override
    public List<Subject> findAllByStudentId(final int studentId) {
        Connection connection = ConnectionManager.getConnection();
        List<Subject> subjects = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_STUDENT_ID)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getInt(Subject.ID));
                    subject.setTitle(rs.getString(Subject.TITLE));
                    subject.setCoefficient(rs.getInt(Subject.COEFFICIENT));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not get subjects by student id", e);
        }
        return subjects;
    }

    @Override
    public List<Integer> findAllIds() {
        Connection connection = ConnectionManager.getConnection();
        List<Integer> ids = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet rs = s.executeQuery(FIND_ALL_IDS)) {
            while (rs.next()) {
                ids.add(rs.getInt(Subject.ID));
            }
        } catch (SQLException e) {
            LOG.error("Can not find all ids", e);
        }
        return ids;
    }
}
