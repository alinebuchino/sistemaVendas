package io.github.alinebuchino.service;

import io.github.alinebuchino.domain.entity.Pedido;
import io.github.alinebuchino.domain.enums.StatusPedido;
import io.github.alinebuchino.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto (Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}