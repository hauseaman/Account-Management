package com.vti.testing.form.account;

import com.vti.testing.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;

@Data
@NoArgsConstructor

public class UpdatingAccountForm {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Role role;
    private int departmentId;



    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.EMPLOYEE;
        }
    }
}
