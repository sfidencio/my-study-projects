package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;

public interface LivroService {
    Livro salvar(Livro livro);
    Livro atualizar(Livro livro);

    Livro buscarPorId(Long id);

    void excluir(Long id);
}
