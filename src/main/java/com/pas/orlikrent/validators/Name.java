package com.pas.orlikrent.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotEmpty(message = "field cannot be empty")
@Size(max = 64, message = "Text too long")
@Pattern(regexp = ValidationRegex.NAME, message = "Invalid text")
public @interface Name {
    String message() default "Invalid text";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
