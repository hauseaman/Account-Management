package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.entity.Department;
import com.vti.testing.form.deparment.CreatingDepartmentForm;
import com.vti.testing.form.deparment.DepartmentFilterForm;
import com.vti.testing.form.deparment.UpdatingDepartmentForm;
import com.vti.testing.repository.IAccountRepository;
import com.vti.testing.repository.IDepartmentRepository;
import com.vti.testing.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AccountService accountService;
    @Override
    public Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm form) {
        Specification<Department> where = DepartmentSpecification.buildWhere(form);
        return departmentRepository.findAll(where, pageable);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void createDepartment(CreatingDepartmentForm form) {
        Department department = modelMapper.map(form, Department.class);
        departmentRepository.save(department);
        List<Account> accounts = department.getAccounts();
        accounts.forEach(account -> account.setDepartment(department));
        accountRepository.saveAll(accounts);
    }

    @Override
    public void updateDepartment(UpdatingDepartmentForm form) {
        Department department = modelMapper.map(form, Department.class);
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(int id) {
            departmentRepository.deleteById(id);
        }


    @Override
    public boolean isDepartmentNameExists(String name) {
        return departmentRepository.existsByName(name);
    }

    @Override
    public boolean isDepartmentIdExists(int id) {
        return departmentRepository.existsById(id);
    }
}
