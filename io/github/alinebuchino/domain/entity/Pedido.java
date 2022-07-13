package io.github.alinebuchino.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @ManyToOne // muitos pedidos para um cliente
    @JoinColumn (name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(length = 20, precision = 20, scale = 2) // 20 caracteres e 2 pontos flutuantes, ex: 1000.00
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;
}
