package com.mentorship.model;

import java.util.List;

public class Subject {

    public static final String TABLE_NAME = "subject";
    public static final String ID = "subject_id";
    public static final String COEFFICIENT = "coefficient";
    public static final String TITLE = "title";

    private int id;
    private String title;
    private int coefficient;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coefficient=" + coefficient +
                ", students=" + students +
                '}' + "\n";
    }
}
