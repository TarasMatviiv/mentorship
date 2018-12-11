package com.mentorship.servlet;

import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.service.StudentService;
import com.mentorship.service.SubjectService;
import com.mentorship.service.impl.StudentServiceImpl;
import com.mentorship.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class StudentServlet extends HttpServlet {

    private StudentService studentService;
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentServiceImpl();
        subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> s = studentService.findAllStudents();
//        Student s = studentService.findStudent(7);
        List<Subject> subjects = subjectService.findAllSubjects();

        req.setAttribute("students", s);
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher(Pages.STUDENTS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String subjectIds[] = req.getParameterValues("subjectIdsCheckbox");

        try {
            studentService.createStudent(name, age, subjectIds);
            doGet(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher(Pages.ERROR).forward(req, resp);
        }
    }
}


