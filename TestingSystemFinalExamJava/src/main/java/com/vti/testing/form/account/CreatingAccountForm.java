package com.vti.testing.form.account;

import com.vti.testing.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class CreatingAccountForm {
    @Length(max = 50, message = "{Account.createAccount.form.name.Length}")
    @NotBlank(message = "{Account.createAccount.form.name.NotBlank}")
    private String username;
    @Length(max = 800,message = "Password's length is max 800")
    @NotBlank(message = "password must not null")
    private String password;
    @Length(max = 50, message = "Username's length is max 50")
    @NotBlank(message = "firstname must not null")
    private String firstname;
    @Length(max = 50, message = "Username's length is max 50")
    @NotBlank(message = "lastname must not null")
    private String lastname;
    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER", message = "Role must be ADMIN, EMPLOYEE, MANAGER")

    private Role role;

}
