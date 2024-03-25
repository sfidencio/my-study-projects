package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;

public interface LivroService {
    Livro salvar(Livro livro);
    Livro atualizar(Livro livro);
}
