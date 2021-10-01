package com.dio.alexander.carrinho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemCarrinhoNotFoundException extends Exception {

    public ItemCarrinhoNotFoundException(Long id) {
        super("Item não encontrado com ID::" + id);
    }
}
