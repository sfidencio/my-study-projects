package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

public interface LivroService {
    Livro salvar(Livro livro);
    Livro atualizar(Livro livro);

    Livro buscarPorId(Long id);

    void excluir(Long id);

    Page<Livro> buscarTodos(String titulo, @PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC) Pageable pageable);

    Page<Livro> buscarTodos(Livro filtro, Pageable pageable);
}
