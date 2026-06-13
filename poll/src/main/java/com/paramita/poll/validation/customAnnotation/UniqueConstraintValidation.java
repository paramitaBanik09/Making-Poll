package com.paramita.poll.validation.customAnnotation;

import com.paramita.poll.validation.customAnnotation.validationClass.UniqueConstraintValidationClass;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConstraintValidationClass.class)
public @interface UniqueConstraintValidation{
    String message() default "Option value should be unique!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
