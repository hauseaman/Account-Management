package com.vti.testing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInforDTO {
    private int id;
    private String username;
    private String fullName;
}
