package com.mentorship.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = Student.TABLE_NAME)
public class Student implements Serializable {

    public static final String TABLE_NAME = "student";
    public static final String ID = "student_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID)
    private int id;

    @Column(name = NAME)
    private String name;

    @Column(name = AGE)
    private int age;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_subject", schema = "men",
            joinColumns = {@JoinColumn(name = ID, referencedColumnName = ID)},
            inverseJoinColumns = {@JoinColumn(name = Subject.ID, referencedColumnName = Subject.ID)}
    )
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
