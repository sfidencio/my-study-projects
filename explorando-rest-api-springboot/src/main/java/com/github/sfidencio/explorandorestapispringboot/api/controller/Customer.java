package com.github.sfidencio.explorandorestapispringboot.api.controller;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.UUID;

@JacksonXmlRootElement
public record Customer(UUID id, String name, String email) {
}
