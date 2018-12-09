package com.mentorship.model;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = Subject.TABLE_NAME)
public class Subject implements Serializable {

    public static final String TABLE_NAME = "subject";
    public static final String ALL = "subject.*";
    public static final String ID = "subject_id";
    public static final String COEFFICIENT = "coefficient";
    public static final String TITLE = "title";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID)
    private int id;

    @Column(name = TITLE)
    private String title;

    @Column(name = COEFFICIENT)
    private int coefficient;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
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
