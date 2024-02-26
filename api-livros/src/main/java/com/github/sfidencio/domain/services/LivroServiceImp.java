package com.github.sfidencio.domain.services;

import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;
import com.github.sfidencio.infrastructure.repository.LivroRepository;
import org.springframework.stereotype.Service;


@Service
public class LivroServiceImp implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImp(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro salvar(Livro livro) {
        if (this.livroRepository.existsByIsbn(livro.getIsbn()))
            throw new BusinessException("ISBN já cadastrado.");
        return this.livroRepository.save(livro);
    }

    @Override
    public Livro atualizar(Livro livro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
