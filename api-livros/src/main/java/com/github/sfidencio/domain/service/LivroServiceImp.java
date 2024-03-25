package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;
import com.github.sfidencio.infrastructure.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LivroServiceImp implements LivroService {

    private final LivroRepository livroRepository;

    @Override
    public Livro salvar(Livro livro) {
        if (this.livroRepository.existsByIsbn(livro.getIsbn())) throw new BusinessException("ISBN já cadastrado.");
        return this.livroRepository.save(livro);
    }

    @Override
    public Livro atualizar(Livro livro) {
        var livroSalvo = this.livroRepository.findById(livro.getId())
                .orElseThrow(() -> new BusinessException("Livro não encontrado."));
        livroSalvo.setTitulo(livro.getTitulo());
        livroSalvo.setAutor(livro.getAutor());
        return this.livroRepository.save(livroSalvo);
    }

}
