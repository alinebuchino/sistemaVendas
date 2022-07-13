package io.github.alinebuchino.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

//{
//     "cliente": 1,
//     "total": 100,
//     "itens": [
//       {
//        "produtos": 1,
//        "quantidade": 10
//       }
//     ]
// } Exemplo do modelo da API

// Cria o modelo da API para apresentar os itens de pedidos

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
