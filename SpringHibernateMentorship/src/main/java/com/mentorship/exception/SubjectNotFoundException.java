package com.mentorship.exception;

public class SubjectNotFoundException extends Exception {
    public SubjectNotFoundException() {
        super("Subject is not found");
    }
}
