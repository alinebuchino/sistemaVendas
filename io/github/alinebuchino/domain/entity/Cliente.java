package io.github.alinebuchino.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // pra dizer ao JPA que Cliente é uma entidade do BD
@Table(name = "cliente") // utiliza caso queira colocar um nome diferente na tabela, por padrão seria o nome da classe
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // representa o auto_increment
    @Column(name = "id") // utiliza caso queira colocar um nome diferente no campo da tabela
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo NOME é obrigatório!")
    private String nome;

    @NotEmpty(message = "Campo CPF é obrigatório!")
    @CPF(message = "Informe um CPF válido!")
    @Column(name = "cpf", length = 11)
    private String cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    // um cliente para muitos pedidos, fetch = trazer junto os pedidos
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}