package com.github.sfidencio.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectUserEntity {
    @EmbeddedId
    private ProjectUserID projectUserID;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private ProjectEntity project;
}
