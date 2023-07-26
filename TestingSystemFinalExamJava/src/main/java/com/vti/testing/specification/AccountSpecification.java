package com.vti.testing.specification;

import com.vti.testing.entity.Account;
import com.vti.testing.entity.Department;
import com.vti.testing.entity.Role;
import com.vti.testing.form.account.AccountFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AccountSpecification {
    private static final String USERNAME = "username";
    private static final String LASTNAME = "last_name";
    private static final String FIRSTNAME = "first_name";
    private static final String ROLE = "role";
    private static final String DEPARTMENT_NAME = "departmentName";

    public static Specification<Account> buildWhere(AccountFilterForm form) {
        if (form == null) {
            return null;
        }
        Specification<Account> whereUsername = new CustomSpecification(USERNAME, form.getSearch());
        Specification<Account> whereLastname = new CustomSpecification(LASTNAME, form.getSearch());
        Specification<Account> whereMFirstName = new CustomSpecification(FIRSTNAME, form.getSearch());
        Specification<Account> whereRole = new CustomSpecification(ROLE, form.getRole());
        Specification<Account> whereDepartmentName = new CustomSpecification(DEPARTMENT_NAME, form.getDepartmentName());

        return Specification.where(whereUsername.or(whereMFirstName).or(whereLastname)).and(whereDepartmentName.or(whereRole));
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Account> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key){
                case USERNAME:
                    return criteriaBuilder.like(root.get("username"),"%" + value + "%");
                case FIRSTNAME:
                    return criteriaBuilder.like(root.get("firstname"),"%" + value +"%");
                case LASTNAME:
                    return criteriaBuilder.like(root.get("lastname"),"%" + value +"%");
                case  DEPARTMENT_NAME:
                   Join<Account,Department> departmentJoin = root.join("department");
                   return criteriaBuilder.like(departmentJoin.get("name"),"%"+value+"%");
                case ROLE:
                    return criteriaBuilder.equal(root.get("role"), Role.valueOf(value.toString()));

                default:
                    return null;
            }
        }
    }
}
