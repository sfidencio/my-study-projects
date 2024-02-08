package com.github.sfidencio.domain.services;

import com.github.sfidencio.domain.model.Livro;

public interface LivroService {
    Livro salvar(Livro livro);
    Livro atualizar(Livro livro);
}
