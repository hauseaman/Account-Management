package com.vti.testing.specification;

import com.vti.testing.entity.Department;
import com.vti.testing.form.deparment.DepartmentFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DepartmentSpecification {
    private static final String NAME = "name";
    private static final String MIN_CREATED_DATE = "minCreatedDate";
    private static final String MAX_CREATED_DATE = "maxCreatedDate";
    private static final String TYPE = "type";

    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
        if (form == null) {
            return null;
        }
        Specification<Department> whereName = new CustomSpecification(NAME, form.getSearch());
        Specification<Department> whereMinCreatedDate = new CustomSpecification(MIN_CREATED_DATE, form.getMinCreatedDate());
        Specification<Department> whereMaxCreatedDate = new CustomSpecification(MAX_CREATED_DATE, form.getMaxCreatedDate());
        Specification<Department> whereType = new CustomSpecification(TYPE, form.getType());
        return Specification.where(whereName).and((whereMinCreatedDate).and(whereMaxCreatedDate).or(whereType));
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Department> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {
                case NAME:
                    return criteriaBuilder.like(root.get("name"),"%" + value + "%");
                case MIN_CREATED_DATE:
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get("createdDate").as(java.sql.Date.class),
                            (Date) value
                    );
                case MAX_CREATED_DATE:
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get("createdDate").as(java.sql.Date.class),
                            (Date) value
                    );
                case TYPE:
                    return criteriaBuilder.equal(root.get("type"), value);
            }
            return null;
        }
    }
}
