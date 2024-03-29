package com.github.sfidencio.infrastructure.repository;

import com.github.sfidencio.domain.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByIsbn(String isbn);
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
