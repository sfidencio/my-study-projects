package com.github.sfidencio.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectUserID {
    private Integer projectId;
    private String functionalCode;
}
