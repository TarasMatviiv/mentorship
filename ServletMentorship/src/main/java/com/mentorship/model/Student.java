package com.mentorship.model;

import com.mentorship.persistent.DaoManager;

import java.util.List;

public class Student {

    public static final String TABLE_NAME = "student";
    public static final String ID = "student_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    private int id;
    private String name;
    private int age;
    private List<Subject> subjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Subject> getSubjects() {
        if(subjects == null) {
            subjects = DaoManager.getSubjectDao().findAllByStudentId(id);
        }
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", subjects=" + subjects +
                '}' + "\n";
    }
}
