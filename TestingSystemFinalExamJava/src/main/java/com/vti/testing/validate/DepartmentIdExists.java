package com.vti.testing.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentIdExistsValidator.class)
public @interface DepartmentIdExists {
    String message() default "Department id not exists";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
