package io.github.alinebuchino.domain.repository;

import io.github.alinebuchino.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
