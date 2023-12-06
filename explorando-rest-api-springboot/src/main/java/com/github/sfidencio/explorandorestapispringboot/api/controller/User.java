package com.github.sfidencio.explorandorestapispringboot.api.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String login;
    private String password;
}
