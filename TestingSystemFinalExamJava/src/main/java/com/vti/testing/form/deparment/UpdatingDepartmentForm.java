package com.vti.testing.form.deparment;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
public class UpdatingDepartmentForm {
    private int id;
    @Length(max = 50, message = "Name's length is max 50")
    @NotBlank(message = "Name must not be blank")
    private String name;
    private int totalMember;
    @Pattern(regexp = "Dev|Test|ScrumMaster|PM", message = "Type must be Dev, Test, ScrumMaster, PM")
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

}

