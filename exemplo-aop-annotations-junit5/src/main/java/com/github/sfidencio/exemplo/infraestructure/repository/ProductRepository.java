package com.github.sfidencio.exemplo.infraestructure.repository;


import com.github.sfidencio.exemplo.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
