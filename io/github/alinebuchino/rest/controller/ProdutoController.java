package io.github.alinebuchino.rest.controller;

import io.github.alinebuchino.domain.entity.Produto;
import io.github.alinebuchino.domain.repository.Produtos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import java.util.List;

@RestController // permite receber requisições
@RequestMapping("/api/produtos")
@Api("API DE PRODUTOS")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @ApiOperation(value = "Busca produtos por id")
    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id) { // pathVariable é o parametro que será passado na url
        return produtos
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @ApiOperation(value = "Salva produtos")
    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody @Valid Produto produto) { // RequestBody é o que irá entrar
        return produtos.save(produto);
    }

    @ApiOperation(value = "Deleta produtos")
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        produtos.findById(id)
                .map(p -> {
                    produtos.delete(p);
                    return p;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @ApiOperation(value = "Atualiza produtos")
    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Produto produto) {
        produtos
                .findById(id)
                .map(produtoExistente -> { // se encontrar o id ele mapeia o cliente setando o que desejar
                    produto.setId(produtoExistente.getId());
                    produtos.save(produto);
                    return produtoExistente;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @ApiOperation(value = "Busca de produtos")
    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher // ExampleMatcher é um objeto que permite tratar algumas configurações para encontrar os clientes
                .matching()
                .withIgnoreCase() // ignorar letra maiuscula ou minuscula
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING); // encontra a string que vc passou
        Example example = Example.of(filtro, matcher); // pega as propriedades que estão populadas
        return produtos.findAll(example);
    }
}