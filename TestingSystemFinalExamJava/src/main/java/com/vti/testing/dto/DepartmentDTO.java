package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.testing.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private int id;
    private String name;
    @JsonProperty("total_member")
    private int totalMember;
    private Type type;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    private List<Account> accounts;

    @Data
    @NoArgsConstructor
    public static class Account {
        private int id;
        private String username;
    }
}
