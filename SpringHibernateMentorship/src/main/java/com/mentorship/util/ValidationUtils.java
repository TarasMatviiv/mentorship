package com.mentorship.util;

import com.mentorship.exception.MandatoryValuesMissingException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidationUtils {

    public static void validateNotNull(Object parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNotEmpty(String parameter, String message) throws MandatoryValuesMissingException {
        if (isBlank(parameter)) {
            throw new MandatoryValuesMissingException(message);
        }
    }
}
