package com.vti.testing.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentNameExistsValidator.class)
public @interface DepartmentNameNotExists {
    String message() default "Department Name existed";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
