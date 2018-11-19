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

public class StudentDaoImpl implements StudentDao {

    private static final String FIND_ALL = "SELECT * FROM student";
    private static final String FIND_BY_ID = "SELECT * FROM student WHERE " + Student.ID + " = ?";
    private static final String FIND_ALL_STUDENTS_IDS_BY_SUBJECT_ID = "SELECT " + Student.ID + " FROM student_subject WHERE " + Subject.ID + " = ?";

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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return student;
    }
}
