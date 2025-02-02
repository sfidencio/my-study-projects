package com.github.sfidencio.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ProjectResponse {
    @Getter
    private Integer id;
    @Getter
    private String name;
    @Getter
    private List<String> users = new ArrayList<>(0);

    public ProjectResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addUser(String user) {
        this.users.add(user);
    }
}
