package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LivroDTO atualizar(@Valid @PathVariable Long id, @RequestBody LivroDTO dto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LivroDTO buscarPorId(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable Long id);
}
