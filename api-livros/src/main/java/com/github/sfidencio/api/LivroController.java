package com.github.sfidencio.api;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sfidencio.api.dto.LivroDTO;
import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.domain.services.LivroService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

//@RestController já inclui a anotação @Controller e @ResponseBody
@RestController
@RequestMapping("/v1/livros")
@Log4j2
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

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
    public LivroDTO criar(@Valid @RequestBody LivroDTO dto) {
        log.info("Criando um livro {}", dto);
        ModelMapper modelMapper = new ModelMapper();
        var livro = new Livro();
        modelMapper.map(dto, livro);
        var livroSalvo = this.livroService.salvar(livro);
        return modelMapper.map(livroSalvo, LivroDTO.class);
    }
}
