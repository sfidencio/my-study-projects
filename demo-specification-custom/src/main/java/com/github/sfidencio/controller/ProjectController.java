package com.github.sfidencio.controller;

import com.github.sfidencio.controller.dto.ProjectRequest;
import com.github.sfidencio.controller.dto.ProjectResponse;
import com.github.sfidencio.domain.entities.ProjectEntity;
import com.github.sfidencio.domain.entities.ProjectUserEntity;
import com.github.sfidencio.domain.entities.ProjectUserID;
import com.github.sfidencio.infra.ProjectRespository;
import com.github.sfidencio.infra.ProjectUserRespository;
import com.github.sfidencio.infra.spec.ProjectSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRespository projectRespository;
    private final ProjectUserRespository projectUserRespository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createProject(@RequestBody List<ProjectRequest> projectRequest) {

        projectRequest.forEach(item -> {
            var projectEntitySaved = this.projectRespository.save(
                    ProjectEntity.builder()
                            .name(item.getName())
                            .build()
            );

            this.projectUserRespository.saveAll(
                    item.
                            getUsers().
                            stream().
                            map(user -> ProjectUserEntity.builder()
                                    .projectUserID(new ProjectUserID(projectEntitySaved.getId(), user))
                                    .build())
                            .toList()
            );

        });
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjects(@RequestParam(defaultValue = "0") int pageNumber,
                                  @RequestParam(defaultValue = "2") int pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var responseProjects = this.projectRespository.findAll(pageable).getContent()
                .stream().map(
                        item -> new ProjectResponse(item.getId(), item.getName())
                ).toList();

        for (var item : responseProjects) {
            var userEntities = this.projectUserRespository.findAllByProjectUserIDProjectId(item.getId());
            for (var userEntity : userEntities) {
                item.addUser(userEntity.getProjectUserID().getFunctionalCode());
            }
        }

        return new PageImpl<>(responseProjects, pageable, responseProjects.size());

    }

    @GetMapping("/spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjectsByCriteria(@RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "2") int pageSize,
                                            @RequestParam String name) {
        var pa = PageRequest.of(pageNumber, pageSize);
        Page<ProjectEntity> projects = this.projectRespository.findAll(ProjectSpecification.getProjectSpecificationByName(name), pa);
        return projects;
    }


}
