package com.github.sfidencio.infra;

import com.github.sfidencio.domain.entities.ProjectUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUserRespository extends JpaRepository<ProjectUserEntity, Integer> {
    List<ProjectUserEntity> findAllByProjectUserIDProjectId(Integer projectId);
}
