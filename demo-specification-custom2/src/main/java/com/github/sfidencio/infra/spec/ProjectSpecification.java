package com.github.sfidencio.infra.spec;

import com.github.sfidencio.domain.entities.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;


public class ProjectSpecification {
    public static Specification<ProjectEntity> getProjectSpecificationByParameter(String parameter) {
        return (root, query, cb) -> {
            cb.like(root.get("name"), "%" + parameter + "%");
            cb.or(cb.like(root.get("key"), "%" + parameter + "%"));
            return cb.and();
        };
    }
}
