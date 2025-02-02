package com.github.sfidencio.infra;

import com.github.sfidencio.domain.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProjectRespository extends JpaRepository<ProjectEntity, Integer>, JpaSpecificationExecutor<ProjectEntity> {
}
