package com.testprojects.socnet.util.exception;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides BadRequestException
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public BadRequestException(final Class<?> resourceName, final String fieldName, final Object fieldValue) {
        this.resourceName = resourceName.getSimpleName();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public String getMessage() {
        return MessageFormatter.arrayFormat("Bad request in resource:'{}' for field:'{}' with value:'{}'",
                new Object[]{resourceName, fieldName, fieldValue}).getMessage();
    }
}
