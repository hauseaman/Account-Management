package com.vti.testing.form.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private String role;
    private String departmentName;
}
