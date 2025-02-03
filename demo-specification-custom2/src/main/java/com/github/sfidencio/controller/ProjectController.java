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
import org.springframework.data.domain.Pageable;
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
            //Save projects parent
            var projectEntitySaved = this.projectRespository.save(ProjectEntity
                    .builder()
                    .name(item.getName())
                    .key(item.getKey())
                    .build());

            //Create composite-key
            item.getUsers().forEach(user -> {
                var projectUserID = ProjectUserID.builder()
                        .projectId(projectEntitySaved.getId())
                        .functionalCode(user.trim())
                        .build();

                var projectUserEntity = ProjectUserEntity.builder()
                        .projectUserID(projectUserID)
                        .project(projectEntitySaved)
                        .build();

                //Save users in parent
                this.projectUserRespository.save(projectUserEntity);
            });

        });
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjects(@RequestParam(defaultValue = "0") int pageNumber,
                                  @RequestParam(defaultValue = "2") int pageSize) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return this.projectRespository.findAll(pageable);
    }

    @GetMapping("/spec-1")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjectsByCriteria1(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "2") int pageSize,
                                             @RequestParam(defaultValue = "") String parameter) {

        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return this.projectRespository.findAll(ProjectSpecification.findProjectSpecificationByParameter(parameter), pageable);

    }

    @GetMapping("/spec-2")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjectsByCriteria2(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "2") int pageSize,
                                             @RequestParam(defaultValue = "") String parameter) {

        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return this.projectRespository.findAll(ProjectSpecification.findProjectSpecificationByParameterWithPredicate(parameter), pageable);

    }

    @GetMapping("/spec-3-mapper")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getAllProjectsByCriteria3(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "2") int pageSize,
                                             @RequestParam(defaultValue = "") String parameter) {

        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        var projects = this.projectRespository.findAll(ProjectSpecification.findProjectSpecificationByParameterWithPredicate(parameter), pageable)
                .getContent().stream().map(prj -> {
                    var projectResponse = new ProjectResponse(prj.getId(), prj.getName(), prj.getKey());
                    prj.getUsers().forEach(item -> {
                        projectResponse.addUser(item.getProjectUserID().getFunctionalCode());
                    });
                    return projectResponse;
                }).toList();

        return new PageImpl<>(projects, pageable, projects.size());
    }


}
