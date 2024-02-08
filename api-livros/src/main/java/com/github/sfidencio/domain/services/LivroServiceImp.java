package com.github.sfidencio.domain.services;

import org.springframework.stereotype.Service;

import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.infrastructure.repository.LivroRepository;


@Service
public class LivroServiceImp implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImp(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro salvar(Livro livro) {
        return this.livroRepository.save(livro);
    }

    @Override
    public Livro atualizar(Livro livro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
