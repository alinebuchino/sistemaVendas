package io.github.alinebuchino.rest.controller;

import io.github.alinebuchino.domain.entity.Cliente;
import io.github.alinebuchino.domain.repository.Clientes;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController // permite receber requisições
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @ApiOperation(value = "Busca clientes por ID")
    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) { // pathVariable é o parametro que será passado na url
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @ApiOperation(value = "Salva clientes")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) { // RequestBody é o que irá entrar
        return clientes.save(cliente);
    }

    @ApiOperation(value = "Deleta clientes")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @ApiOperation(value = "Atualiza clientes")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> { // se encontrar o id ele mapeia o cliente setando o que desejar
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @ApiOperation(value = "Busca de clientes")
    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher // ExampleMatcher é um objeto que permite tratar algumas configurações para encontrar os clientes
                .matching()
                .withIgnoreCase() // ignorar letra maiuscula ou minuscula
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING); // encontra a string que vc passou
        Example example = Example.of(filtro, matcher); // pega as propriedades que estão populadas
        return clientes.findAll(example);
    }
}
