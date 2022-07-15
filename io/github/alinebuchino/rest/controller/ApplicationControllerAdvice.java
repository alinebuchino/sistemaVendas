package io.github.alinebuchino.rest.controller;

import io.github.alinebuchino.exception.PedidoNaoEncontradoException;
import io.github.alinebuchino.exception.RegraNegocioException;
import io.github.alinebuchino.rest.APIErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new APIErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
        return new APIErrors(ex.getMessage());
    }
}
