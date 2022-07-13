package io.github.alinebuchino.service.impl;

import io.github.alinebuchino.domain.repository.Pedidos;
import io.github.alinebuchino.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
