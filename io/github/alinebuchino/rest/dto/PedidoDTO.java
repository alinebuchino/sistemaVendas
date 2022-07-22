package io.github.alinebuchino.rest.dto;

import io.github.alinebuchino.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}
