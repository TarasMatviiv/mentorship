package com.mentorship.servlet;

import com.mentorship.model.Student;
import com.mentorship.service.StudentService;
import com.mentorship.service.impl.StudentServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Student> students = studentService.findAllStudents();

        if (students.isEmpty()) {
            req.setAttribute("error", "Students is missing");
            req.getRequestDispatcher(Pages.ERROR).forward(req, resp);
        } else {
            req.setAttribute("students", students);
            req.getRequestDispatcher(Pages.STUDENTS).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter(Student.NAME);
        String age = req.getParameter(Student.AGE);
        String subjectIdsAsString = req.getParameter("subjectIds");
        System.out.println(name);
        System.out.println(age);
        System.out.println(subjectIdsAsString);

        doGet(req, resp);
    }
}


//        resp.getWriter().print("Hello from STUDENT servlet");
