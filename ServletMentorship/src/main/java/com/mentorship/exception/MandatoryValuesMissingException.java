package com.mentorship.exception;

public class MandatoryValuesMissingException extends Exception {

    public MandatoryValuesMissingException() {
        super("Please, fill mandatory fields");
    }
}
