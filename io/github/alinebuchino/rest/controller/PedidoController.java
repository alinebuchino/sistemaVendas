package io.github.alinebuchino.rest.controller;

import io.github.alinebuchino.domain.entity.ItemPedido;
import io.github.alinebuchino.domain.entity.Pedido;
import io.github.alinebuchino.domain.enums.StatusPedido;
import io.github.alinebuchino.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.alinebuchino.rest.dto.InformacoesItemsPedidoDTO;
import io.github.alinebuchino.rest.dto.InformacoesPedidoDTO;
import io.github.alinebuchino.rest.dto.PedidoDTO;
import io.github.alinebuchino.service.PedidoService;

import static org.springframework.http.HttpStatus.*;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado!"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converterItens(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemsPedidoDTO> converterItens(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) { // se a lista não tiver itens
            return Collections.emptyList(); // retorna uma lista vazia ou invés de uma lista cheia de null dentro
        }
        return itens.stream().map(
                itemPedido -> InformacoesItemsPedidoDTO.builder()
                        .descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
