package com.mentorship.servlet;

import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.service.StudentService;
import com.mentorship.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/home/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String getStudents(final Model model) {
        List<Student> s = studentService.findAllStudents();
        List<Subject> subjects = subjectService.findAllSubjects();

        model.addAttribute("students", s);
        model.addAttribute("subjects", subjects);
        return Pages.STUDENTS;
    }

    @PostMapping
    public String createStudent(@RequestParam final String name,
                                @RequestParam final String age,
                                @RequestParam(required = false) final List<Integer> subjectIds,
                                final Model model) {
        String page;
        try {
            studentService.createStudent(name, age, subjectIds);
            page = getStudents(model);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            e.printStackTrace();
            page = Pages.ERROR;
        }
        return page;
    }
}


