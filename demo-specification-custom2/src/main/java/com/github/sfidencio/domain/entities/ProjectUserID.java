package com.github.sfidencio.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProjectUserID {
    private Integer projectId;
    private String functionalCode;
}
