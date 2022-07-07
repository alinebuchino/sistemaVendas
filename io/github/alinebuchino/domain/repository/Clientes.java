package io.github.alinebuchino.domain.repository;

import io.github.alinebuchino.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    //List<Cliente> findByNomeLike(String nome);
    //List<Cliente> findByNomeOrId(String nome, Integer id); // buscar por nome ou id
    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id); // buscar por nome ou id ordenando por Id
    //boolean existsByNome(String nome); // verificar se o nome existe ... etc

    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying // indica que o método não é apenas uma busca, precisa de uma transação no BD
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id")Integer id);
}