package com.mentorship.exception;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
        super("Student is not found");
    }
}
