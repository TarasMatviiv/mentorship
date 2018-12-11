package com.mentorship.servlet;

import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;
import com.mentorship.service.SubjectService;
import com.mentorship.service.impl.SubjectServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubjectServlet extends HttpServlet {

    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectService.findAllSubjects();
        subjects.forEach(subject -> System.out.println(subject.getStudents() + "***"));
        req.setAttribute("subjects", subjects);

        String title = req.getParameter("title");
        if (StringUtils.isNotBlank(title)) {
            try {
                Subject subject = subjectService.findSubjectByTitle(title);
                req.setAttribute("subject", subject);
            } catch (SubjectNotFoundException e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher(Pages.ERROR).forward(req, resp);
                e.printStackTrace();
                return;
            }
        }
        req.getRequestDispatcher(Pages.SUBJECTS).forward(req, resp);
    }
}
