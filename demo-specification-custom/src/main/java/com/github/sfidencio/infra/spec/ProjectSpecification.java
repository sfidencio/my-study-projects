package com.github.sfidencio.infra.spec;

import com.github.sfidencio.domain.entities.ProjectEntity;
import com.github.sfidencio.domain.entities.ProjectUserEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class ProjectSpecification {
    public static Specification<ProjectEntity> getProjectSpecificationByName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProjectEntity> getProjectAndUsersSpecificationByName(String name) {
        return (root, query, cb) -> {

//            assert query != null;
//            Subquery<String> subquery = query.subquery(String.class);
//            Root<ProjectUserEntity> subqueryRoot = subquery.from(ProjectUserEntity.class);
//            subquery.select(subqueryRoot.get("projectUserID").get("functionalCode"))
//                    .where(cb.equal(subqueryRoot.get("projectUserID").get("projectId"), root.get("id")));
//
//            return cb.or(
//                    cb.like(root.get("name"), "%" + name + "%"),
//                    cb.exists(subquery)
//            );

            Join<ProjectEntity, ProjectUserEntity> projectUserJoin = root.join("projectUsers");

            Predicate namePredicate = cb.like(root.get("name"), "%" + name + "%");
            Predicate functionalCodePredicate = cb.like(projectUserJoin.get("functionalCode"), "%" + name + "%");

            return cb.or(namePredicate, functionalCodePredicate);
        };
    }
}
