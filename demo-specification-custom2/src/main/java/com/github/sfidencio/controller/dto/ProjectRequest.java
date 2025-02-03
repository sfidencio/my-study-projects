package com.github.sfidencio.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ProjectRequest {
    private Integer id;
    private String name;
    private String key;
    private List<String> users;
}
