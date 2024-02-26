package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface LivroController {
    /*
     * Exemplo de request via curl:
     * curl -X POST "http://localhost:8080/api/livros" -H "accept: application/json"
     * -H "Content-Type: application/json" -d
     * "{ \"titulo\": \"Meu Livro\", \"autor\": \"Autor\", \"isbn\": \"123\" }"
     *
     * @param dto
     *
     * @return
     *
     * @throws JsonProcessingException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LivroDTO criar(@Valid @RequestBody LivroDTO dto);
}
