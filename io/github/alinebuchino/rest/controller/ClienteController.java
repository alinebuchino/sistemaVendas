package io.github.alinebuchino.rest.controller;

import io.github.alinebuchino.domain.entity.Cliente;
import io.github.alinebuchino.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // permite receber requisições
//@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody // o retorno será dentro do corpo da requisição
    public ResponseEntity getClienteById(@PathVariable Integer id) { // pathVariable é o parametro que será passado na url
        Optional<Cliente> cliente = clientes.findById(id); // Optional pq pode ou não retornar algo

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody // ResponseBody é o que irá retornar
    public ResponseEntity save(@RequestBody Cliente cliente) { // RequestBody é o que irá entrar
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id); // Optional pq pode ou não retornar algo

        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build(); // noContent não retorna nada
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente) {
        return clientes
                .findById(id)
                .map(clienteExistente -> { // se encontrar o id ele mapeia o cliente setando o que desejar
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build()); // se não encontrar o id
    }

    @GetMapping("/api/clientes")
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher // ExampleMatcher é um objeto que permite tratar algumas configurações para encontrar os clientes
                .matching()
                .withIgnoreCase() // ignorar letra maiuscula ou minuscula
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING); // encontra a string que vc passou
        Example example = Example.of(filtro, matcher); // pega as propriedades que estão populadas
        List<Cliente> list = clientes.findAll(example);
        return ResponseEntity.ok(list);
    }
}
