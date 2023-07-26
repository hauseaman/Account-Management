package com.vti.testing.validate;

import com.vti.testing.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentIdExistsValidator implements ConstraintValidator<DepartmentIdExists, Integer> {
    @Autowired
    private IDepartmentService departmentService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return departmentService.isDepartmentIdExists(id);
    }
}
