package com.vti.testing.service;

import com.vti.testing.entity.Department;
import com.vti.testing.form.deparment.CreatingDepartmentForm;
import com.vti.testing.form.deparment.DepartmentFilterForm;
import com.vti.testing.form.deparment.UpdatingDepartmentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {
    Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm form);
    Department getDepartmentById(int id);

    void createDepartment(CreatingDepartmentForm form);

    void updateDepartment( UpdatingDepartmentForm form);
    void deleteDepartment(int id);
    boolean isDepartmentNameExists(String name);
    boolean isDepartmentIdExists(int id);
}
