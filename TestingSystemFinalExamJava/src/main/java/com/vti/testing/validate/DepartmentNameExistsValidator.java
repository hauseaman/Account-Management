package com.vti.testing.validate;

import com.vti.testing.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String> {
    @Autowired
    private IDepartmentService departmentService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !departmentService.isDepartmentNameExists(name);
    }
}
