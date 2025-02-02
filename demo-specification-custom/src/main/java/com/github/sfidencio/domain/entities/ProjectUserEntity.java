package com.github.sfidencio.domain.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
}
