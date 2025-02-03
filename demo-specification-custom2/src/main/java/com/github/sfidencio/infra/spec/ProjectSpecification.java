package com.github.sfidencio.infra.spec;

import com.github.sfidencio.domain.entities.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;


public class ProjectSpecification {
    public static Specification<ProjectEntity> findProjectSpecificationByParameter(String parameter) {
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("name")), "%".concat(parameter.toLowerCase()).concat("%")),
                cb.like(cb.lower(root.get("key")), "%".concat(parameter.toLowerCase()).concat("%"))
        );
    }

    public static Specification<ProjectEntity> findProjectSpecificationByParameterWithPredicate(String parameter) {
        return (root, query, cb) -> {
            final String concat = "%".concat(parameter.toLowerCase()).concat("%");
            var predicate1 = cb.like(cb.lower(root.get("name")), concat);
            var predicate2 = cb.like(cb.lower(root.get("key")), concat);
            return cb.or(predicate1, predicate2);
        };
    }
}
