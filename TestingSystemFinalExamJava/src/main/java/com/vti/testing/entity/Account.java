package com.vti.testing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "`Account`")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "`password`", length = 800, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstname;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastname;

    @Formula("concat(first_name,' ',last_name)")
    private String fullName;

    @Column(name = "`role`", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.EMPLOYEE;
        }
    }
}
