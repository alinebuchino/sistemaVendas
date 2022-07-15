package io.github.alinebuchino.service;

import io.github.alinebuchino.domain.entity.Pedido;
import io.github.alinebuchino.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
