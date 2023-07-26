package com.vti.testing.dto;

import com.vti.testing.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String username;
    private String departmentName;
    private String lastname;
    private String firstname;
    private String fullName;
    private Role role;

}
