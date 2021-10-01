package com.dio.alexander.carrinho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarrinhoNotFoundException extends Exception {

    public CarrinhoNotFoundException() {
        super("Carrinho de compra não encontrado");
    }
}
