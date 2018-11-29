package com.mentorship.dao.impl;

import com.mentorship.dao.StudentDao;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.persistent.ConnectionManager;
import com.mentorship.persistent.DaoManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao {

    private static final Logger LOG = Logger.getLogger(StudentDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM " + Student.TABLE_NAME;
    private static final String FIND_BY_ID = "SELECT * FROM student WHERE " + Student.ID + " = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM student WHERE " + Student.NAME + " = ?";
    private static final String FIND_ALL_STUDENTS_IDS_BY_SUBJECT_ID = "SELECT " + Student.ID + " FROM student_subject WHERE " + Subject.ID + " = ?";
    private static final String CREATE = "INSERT INTO " + Student.TABLE_NAME + " (" + Student.NAME + ", " + Student.AGE + ")  VALUES (?, ?);";
    private static final String CREATE_RELATIONS = "INSERT INTO student_subject (" + Student.ID + ", " + Subject.ID + ")  VALUES (?, ?);";

    @Override
    public List<Student> findAll() {
        Connection connection = ConnectionManager.getConnection();

        List<Student> students = new ArrayList<>();
        try (Statement s = connection.createStatement();
             ResultSet rs = s.executeQuery(FIND_ALL)) {

            while (rs.next()) {
                Student student = new Student();
                int studentId = rs.getInt(Student.ID);
                student.setId(studentId);
                student.setAge(rs.getInt(Student.AGE));
                student.setName(rs.getString(Student.NAME));
                student.setSubjects(DaoManager.getSubjectDao().findAllRawByStudentId(studentId));
                students.add(student);
            }
        } catch (SQLException e) {
            LOG.error("Can not get all students", e);
        }

        return students;
    }

    @Override
    public List<Student> findAllRawBySubjectId(final int subjectId) {
        Connection connection = ConnectionManager.getConnection();
        List<Student> rawStudents = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_STUDENTS_IDS_BY_SUBJECT_ID)) {
            ps.setInt(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt(Student.ID));
                    rawStudents.add(student);
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not get raw students by subject id", e);
        }
        return rawStudents;
    }

    @Override
    public Student findById(final int studentId) {
        Connection connection = ConnectionManager.getConnection();

        Student student = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString(Student.NAME));
                    student.setAge(rs.getInt(Student.AGE));
                    student.setSubjects(DaoManager.getSubjectDao().findAllRawByStudentId(studentId));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not find student by id", e);
        }
        return student;
    }

    @Override
    public Student findByName(final String studentName) {
        Connection connection = ConnectionManager.getConnection();

        Student student = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, studentName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    int studentId = rs.getInt(Student.ID);
                    student.setId(studentId);
                    student.setName(studentName);
                    student.setAge(rs.getInt(Student.AGE));
                    student.setSubjects(DaoManager.getSubjectDao().findAllRawByStudentId(studentId));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can not find student by name", e);
        }
        return student;
    }

    @Override
    public void create(final Student student) {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.executeUpdate();
            createSubjectsRelations(student);
        } catch (SQLException e) {
            LOG.error("Can not create student", e);
        }
    }

    private void createSubjectsRelations(final Student rawStudent) {
        Connection connection = ConnectionManager.getConnection();
        Student student = findByName(rawStudent.getName());

        for (Integer subjectId : getSubjectsIds(rawStudent)) {
            try (PreparedStatement ps = connection.prepareStatement(CREATE_RELATIONS)) {
                ps.setInt(1, student.getId());
                ps.setInt(2, subjectId);
                ps.executeUpdate();
            } catch (SQLException e) {
                LOG.error("Can not create subjects relations", e);
            }
        }
    }

    private List<Integer> getSubjectsIds(final Student student) {
        return student.getSubjects()
                .stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
