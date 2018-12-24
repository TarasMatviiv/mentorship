package com.mentorship.servlet;

import com.mentorship.exception.SubjectNotFoundException;
import com.mentorship.model.Subject;
import com.mentorship.service.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/home/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    protected String getSubjects(@RequestParam(required = false) final String title,
                                 final Model model) {
        List<Subject> subjects = subjectService.findAllSubjects();
//        subjects.forEach(subject -> System.out.println(subject.getStudents() + "***"));
        model.addAttribute("subjects", subjects);

        String page = Pages.SUBJECTS;
        if (StringUtils.isNotBlank(title)) {
            try {
                Subject subject = subjectService.findSubjectByTitle(title);
                model.addAttribute("subject", subject);
            } catch (SubjectNotFoundException e) {
                model.addAttribute("message", e.getMessage());
                page = Pages.ERROR;
            }
        }
        return page;
    }
}
