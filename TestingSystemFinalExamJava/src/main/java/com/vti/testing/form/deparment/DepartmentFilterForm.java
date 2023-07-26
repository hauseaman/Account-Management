package com.vti.testing.form.deparment;

import com.vti.testing.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor

public class DepartmentFilterForm {
    private String search;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxCreatedDate;
    private Type type;

}
