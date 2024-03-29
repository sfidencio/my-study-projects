package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;
import com.github.sfidencio.infrastructure.repository.LivroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LivroServiceImp implements LivroService {


    private final LivroRepository livroRepository;

    @Override
    @Transactional
    public Livro salvar(Livro livro) {
        if (this.livroRepository.existsByIsbn(livro.getIsbn())) throw new BusinessException("ISBN já cadastrado.");
        return this.livroRepository.save(livro);
    }

    @Override
    @Transactional
    public Livro atualizar(Livro livro) {
        var livroSalvo = this.livroRepository.findById(livro.getId()).orElseThrow(() -> new BusinessException("Livro não encontrado."));
        livroSalvo.setTitulo(livro.getTitulo());
        livroSalvo.setAutor(livro.getAutor());
        return this.livroRepository.save(livroSalvo);
    }

    @Override
    public Livro buscarPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow(() -> new BusinessException("Livro não encontrado."));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        var livro = this.livroRepository.findById(id).orElseThrow(() -> new BusinessException("Livro não encontrado."));
        this.livroRepository.delete(livro);
    }

    @Override
    public Page<Livro> buscarTodos(String titulo, Pageable pageable) {
        //Pageable pageable = Pageable.ofSize(size).withPage(page);
        return this.livroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }


}
