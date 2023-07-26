package com.vti.testing.form.deparment;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@NoArgsConstructor
public class CreatingDepartmentForm {
    @Length(max = 50, message = "{Department.createDepartment.form.name.Length}")
    @NotBlank(message = "{Department.createDepartment.form.name.NotBlank}")
//    @DepartmentNameNotExists(message = "{Department.createDepartment.form.name.NotExists}")
    private String name;
    @PositiveOrZero(message = "Total member must greater than or equal zero")
    private int totalMember;
    @Pattern(regexp = "Dev|Test|ScrumMaster|PM", message = "Type must be Dev, Test, ScrumMaster, PM")
    private String type;
    private List<@Valid Account> accounts;

    @Data
    @NoArgsConstructor
    public static class Account {
        @Length(max = 50, message = "Username's length is max 50")
        @NotBlank(message = "Username must not null")
        private String username;
        @Length(max = 50, message = "Username's length is max 50")
        @NotBlank(message = "firstname must not null")
        private String firstname;
        @Length(max = 50, message = "Username's length is max 50")
        @NotBlank(message = "lastname must not null")
        private String lastname;
        @Length(max = 800, message = "Password's length is max 50")
        @NotBlank(message = "password must not null")
        private String password;
        @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER", message = "Type must be ADMIN, EMPLOYEE, MANAGER")


        private String role;

    }
}
