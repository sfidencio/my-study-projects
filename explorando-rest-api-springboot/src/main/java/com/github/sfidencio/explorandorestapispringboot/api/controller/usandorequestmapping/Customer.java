package com.github.sfidencio.explorandorestapispringboot.api.controller.usandorequestmapping;

import java.util.UUID;

public record Customer(UUID id, String name, String email) {
}
