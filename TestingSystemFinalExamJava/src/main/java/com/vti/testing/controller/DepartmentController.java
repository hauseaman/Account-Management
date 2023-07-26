package com.vti.testing.controller;

import com.vti.testing.dto.DepartmentDTO;
import com.vti.testing.entity.Department;
import com.vti.testing.form.deparment.CreatingDepartmentForm;
import com.vti.testing.form.deparment.DepartmentFilterForm;
import com.vti.testing.form.deparment.UpdatingDepartmentForm;
import com.vti.testing.service.IDepartmentService;
import com.vti.testing.validate.DepartmentIdExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/departments")
@Validated
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<DepartmentDTO> getALlDepartments(@PageableDefault(page = 0,size = 5,sort = "totalMember",direction = Sort.Direction.DESC) Pageable pageable, DepartmentFilterForm form) {
        Page<Department> pageDepartments = departmentService.getAllDepartments(pageable,form);
        List<Department> departments = pageDepartments.getContent();
        List<DepartmentDTO> departmentDTOS = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>() {
        }.getType());
        departmentDTOS.forEach(dto -> {
            dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class).getDepartmentById(dto.getId())).withSelfRel());
        });
        return new PageImpl<>(departmentDTOS, pageable, pageDepartments.getTotalElements());
    }

    @GetMapping(value = "/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable(name = "id") @DepartmentIdExists int id) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        departmentDTO.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(id)).withSelfRel());
        return departmentDTO;
    }

    @PostMapping
    public void createDepartment(@RequestBody @Valid CreatingDepartmentForm form){
        departmentService.createDepartment(form);
    }

    @PutMapping(value = "/{id}")
    public void updateDepartment(@PathVariable(name = "id")int id, @RequestBody UpdatingDepartmentForm form){
        form.setId(id);
        departmentService.updateDepartment(form);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteDepartment(@PathVariable(name = "id")int id){
        departmentService.deleteDepartment(id);
    }
}
