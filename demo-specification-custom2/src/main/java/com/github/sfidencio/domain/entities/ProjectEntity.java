package com.github.sfidencio.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String key;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<ProjectUserEntity> users;
}
