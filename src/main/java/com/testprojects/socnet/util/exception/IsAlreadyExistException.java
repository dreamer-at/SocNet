package com.testprojects.socnet.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides IsAlreadyExistException
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */
@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class IsAlreadyExistException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public IsAlreadyExistException(final Class<?> clazz, final String fieldName, final Object fieldValue) {
        super(String.format("%s with %s:'%s' is already exist", clazz.getSimpleName(), fieldName, fieldValue));
        this.resourceName = clazz.getSimpleName();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
