package com.testprojects.socnet.util;

import java.lang.annotation.*;
import io.swagger.annotations.*;

/**
 * Provides annotation for pageable
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataTypeClass = Long.class, dataType = "int", paramType = "query",
                defaultValue = "0", example = "0", value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataTypeClass = Long.class, dataType = "int", paramType = "query",
                defaultValue = "50", example = "0", value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataTypeClass = String.class, dataType = "string",
                paramType = "query", defaultValue = "asc", example = "asc",
                value = "Sorting criteria in the format: property(asc|desc). Multiple sort criteria are supported.")})
public @interface ApiPageable {
}
