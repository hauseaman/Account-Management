package com.vti.testing.repository;

import com.vti.testing.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    Department findById(int id);
    boolean existsByName(String name);
}
