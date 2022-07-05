package io.github.alinebuchino.domain.entity;

import javax.persistence.*;

@Entity // pra dizer ao JPA que Cliente é uma entidade do BD
@Table(name = "Cliente") // utiliza caso queira colocar um nome diferente na tabela, por padrão seria o nome da classe
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // representa o auto_increment
    @Column(name = "id") // utiliza caso queira colocar um nome diferente no campo da tabela
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}