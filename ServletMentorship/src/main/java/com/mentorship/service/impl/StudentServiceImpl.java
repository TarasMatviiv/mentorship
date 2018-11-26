package com.mentorship.service.impl;

import com.mentorship.exception.MandatoryValuesMissingException;
import com.mentorship.model.Student;
import com.mentorship.model.Subject;
import com.mentorship.persistent.DaoManager;
import com.mentorship.service.StudentService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class StudentServiceImpl implements StudentService {

    private static final String VALID_IDS_REGEX = "[\\s\\d,]*";
    private static final String SPACES_REGEX = "\\s+";
    private static final String COMA = ",";

    @Override
    public List<Student> findAllStudents() {
        return DaoManager.getStudentDao().findAll();
    }

    @Override
    public void createStudent(final String name, final String age, final String subjectIdsAsString) throws Exception {
        validMandatory(name, age);
        validIds(subjectIdsAsString);

        Student student = new Student();
        student.setName(name);
        student.setAge(Integer.parseInt(age));

        List<Subject> rawSubjects = createRawSubjectsFromIds(subjectIdsAsString);
        student.setSubjects(rawSubjects);

        DaoManager.getStudentDao().create(student);
    }

    private List<Subject> createRawSubjectsFromIds(String ids) {
        List<Subject> subjects = new ArrayList<>();

        if(isNotBlank(ids)) {
            String withoutSpaces = ids.replaceAll(SPACES_REGEX, "");
            String[] withoutComa = withoutSpaces.split(COMA);

            for(String idAsString: withoutComa) {
                Subject subject = new Subject();
                subject.setId(Integer.parseInt(idAsString));
                subjects.add(subject);
            }
        }

        return subjects;
    }

    private void validIds(String subjectIdsAsString) throws Exception {
        if (!subjectIdsAsString.matches(VALID_IDS_REGEX)) {
            throw new Exception("IDs are not valid");
        }
    }

    private void validMandatory(String name, String age) throws MandatoryValuesMissingException {
        if (isBlank(name) || isBlank(age)) {
            throw new MandatoryValuesMissingException();
        }
    }
}
