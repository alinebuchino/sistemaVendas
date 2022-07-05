package io.github.alinebuchino;

import io.github.alinebuchino.domain.entity.Cliente;
import io.github.alinebuchino.domain.repository.Clientes;
import org.hibernate.cache.spi.CacheImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Aline"));
            clientes.save(new Cliente("Teste"));

            List<Cliente> result = clientes.encontrarPorNome("Aline");
            result.forEach(System.out::println);

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);
//
//            System.out.println("Atualizando clientes");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado.");
//                clientes.save(c);
//            });
//
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Buscando clientes");
//            clientes.findByNomeLike("Aline").forEach(System.out::println);

//            System.out.println("deletando clientes");
//            clientes.findAll().forEach(c -> {
//                clientes.delete(c);
//            });

            todosClientes = clientes.findAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}