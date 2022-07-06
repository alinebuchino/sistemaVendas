package io.github.alinebuchino.domain.repository;

import io.github.alinebuchino.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
