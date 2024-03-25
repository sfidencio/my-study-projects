package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.domain.service.LivroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController já inclui a anotação @Controller e @ResponseBody
@RestController
@RequestMapping("/v1/livros")
@Log4j2
@RequiredArgsConstructor
public class LivroControllerImp implements LivroController {
    private final LivroService livroService;
    private final ModelMapper modelMapper;

    @Override
    public LivroDTO criar(LivroDTO dto) {
        log.info("Criando um livro {}", dto);
        var livro = this.modelMapper.map(dto, Livro.class);
        var livroSalvo = livroService.salvar(livro);
        return this.modelMapper.map(livroSalvo, LivroDTO.class);
    }

    @Override
    public LivroDTO atualizar(Long id, LivroDTO dto) {
        log.info("Atualizando um livro {}", dto);
        var livro = this.modelMapper.map(dto, Livro.class);
        livro.setId(id);
        var livroAtualizado = livroService.atualizar(livro);
        return this.modelMapper.map(livroAtualizado, LivroDTO.class);
    }
}
