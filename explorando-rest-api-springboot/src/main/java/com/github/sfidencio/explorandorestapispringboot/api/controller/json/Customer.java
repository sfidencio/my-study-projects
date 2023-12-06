package com.github.sfidencio.explorandorestapispringboot.api.controller.json;

import java.util.UUID;

public record Customer(UUID id, String name, String email) {
}
