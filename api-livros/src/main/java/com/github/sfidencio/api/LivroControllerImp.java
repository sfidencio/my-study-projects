package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.domain.service.LivroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController já inclui a anotação @Controller e @ResponseBody
@RestController
@RequestMapping("/api/v1/livros")
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

    @Override
    public LivroDTO buscarPorId(Long id) {
        log.info("Buscando um livro por id {}", id);
        var livro = livroService.buscarPorId(id);
        return this.modelMapper.map(livro, LivroDTO.class);
    }

    @Override
    public void excluir(Long id) {
        log.info("Deletando um livro por id {}", id);
        livroService.excluir(id);
    }
    /*
     * Para adicionar parâmetros implícitos no Springdoc Swagger, você precisa adicionar a anotação @Parameter do pacote io.swagger.v3.oas.annotations em cada parâmetro que você deseja documentar. No caso do método buscarTodos, você pode adicionar a anotação @Parameter para os parâmetros page, size e sort. Aqui está como você pode fazer isso:
     */

    @Override
    public Page<Livro> buscarTodos(String titulo, Pageable pageable) {
        log.info("Buscando todos os livros paginados {} {}", pageable.getPageNumber(), pageable.getPageSize());
        return livroService.buscarTodos(titulo, pageable);
    }
}
